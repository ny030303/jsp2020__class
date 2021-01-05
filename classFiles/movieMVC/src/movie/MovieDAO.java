package movie;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class MovieDAO {
		private static MovieDAO instance = new MovieDAO();
		private MovieDAO() {}
		public static MovieDAO getInstance() {
			return instance;
		}
		
		public Connection getConnection() {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "1234";
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 오라클 연결
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("오라클 접속 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public boolean loginMember(HttpServletRequest request, String id, String pw) {
			boolean result = false;
			try {
				Connection conn = instance.getConnection();
				
				String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					 String email = rs.getString("email");
					 String tel = rs.getString("tel");
					 Date birth = rs.getDate("birth");
					MemberVO member = new MemberVO(id, pw, email, tel, birth);
					
					HttpSession httpSession = request.getSession(true);
					httpSession.setAttribute("USER", member);
					result = true;
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		public void logoutMember(HttpServletRequest request) {
			HttpSession httpSession = request.getSession(true);
			httpSession.invalidate();
		}
		
		public ArrayList<MovieVO> selectMovieList(int category) {
			// 리스트 생성
			ArrayList<MovieVO> list = new ArrayList<MovieVO>();
			try {
				String selectSql = "SELECT * FROM movie";
				Connection conn = instance.getConnection();
				PreparedStatement pstmt = null;
				
				if(category != 0) {
					selectSql = selectSql + " WHERE category = ?";
					pstmt = conn.prepareStatement(selectSql);
					pstmt.setInt(1, category);
				} else {
					pstmt = conn.prepareStatement(selectSql);
				}
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) { // 1줄씩 가져옴.
					int movieNo = rs.getInt("movieNo");
					String movieName = rs.getString("movieName");
					category = rs.getInt("category");
					int runtime = rs.getInt("runtime");
					String img = rs.getString("img");
					String info = rs.getString("info");
					
					MovieVO movie = new MovieVO(movieNo, movieName, category, runtime, img, info);
					// 리스트에 넣기
					list.add(movie);
				}
				// 객체를 생성한 순서의 반대로 close
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// member 정보가 담긴  ArrayList인 list 반환
			return list;
		}
		
		public MovieVO selectAMovie(int movieNo) {
			MovieVO movie = null;
			try {
				String selectSql = "SELECT * FROM movie WHERE movieNo = ?";
				Connection conn = instance.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectSql);
				pstmt.setInt(1, movieNo);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					movieNo = rs.getInt("movieNo");
					String movieName = rs.getString("movieName");
					int category = rs.getInt("category");
					int runtime = rs.getInt("runtime");
					String img = rs.getString("img");
					String info = rs.getString("info");
					
					movie = new MovieVO(movieNo, movieName, category, runtime, img, info);
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return movie;
		}
		
		public ArrayList<ScheduleVO> selectAMovieSchedule(int movieNo) {
			ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
			try {
				String selectSql = "SELECT schno, moviename, decode(category, 01,'액선',02,'로맨스',03,'코미디',04,'스릴러','애니메이션') category," + 
						"mt.runtime, img, info, to_char(runDay, 'mm/dd')as 날짜 ," + 
						"to_char(runDay, 'hh24:mi')as 상영시간, roomNo FROM movie mt, schedule st " + 
						"WHERE mt.movieNo = st.movieNo and mt.movieNo = ?";
				Connection conn = instance.getConnection();
				
				PreparedStatement pstmt = conn.prepareStatement(selectSql);
				pstmt.setInt(1, movieNo);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					ScheduleVO sch = new ScheduleVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
					list.add(sch);
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public int insertMovie(MovieVO movie) {
			int cnt = 0;
			try {
				Connection conn = instance.getConnection();
				String insertSql = "insert into movie values(?,?,?, ?,?,?);";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(insertSql);
				pstmt.setInt(1, movie.getMovieNo());
				pstmt.setString(2, movie.getMovieName());
				pstmt.setInt(3, movie.getCategory());
				pstmt.setInt(4, movie.getRuntime());
				pstmt.setString(5, movie.getImg());
				pstmt.setString(6, movie.getInfo());
				// insert 라서 executeUpdate()
				cnt = pstmt.executeUpdate();
				// 객체를 생성한 순서의 반대로 close
				instance.close(null, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// row 개수 리턴
			return cnt;
		}
		
		public int[] chooseSeat(int schNo) {
			int[] seatInfo = new int[20];
			try {
				//System.out.println("schNo: " + schNo);
				Connection conn = instance.getConnection();
				String sql = "select movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st where tt.schNo = st.schNo and st.schNo = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, schNo);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					seatInfo[rs.getInt("seatNo")] = 1;
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return seatInfo;
		}
		
		public boolean insertTicket(HttpServletRequest request, int schNo, int seatNo) {
			boolean result = false;
			try {
				Connection conn = instance.getConnection();
				
				HttpSession httpSession = request.getSession(true);
				MemberVO user = (MemberVO) httpSession.getAttribute("USER");
				
				String insertSql = "insert into ticket values((select NVL(max(ticketNo),0) from ticket)+1,SYSDATE,?,?,?)";
				String roomSql = "update room set seatCnt = seatCnt +1 where schNo = ?";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(insertSql);
				pstmt.setInt(1, schNo);
				pstmt.setInt(2, seatNo);
				pstmt.setString(3, user.getId());
				int insert_cnt = pstmt.executeUpdate(); 
				
				pstmt = conn.prepareStatement(roomSql);
				pstmt.setInt(1, schNo);
				int room_cnt = pstmt.executeUpdate(); 
				
				if(insert_cnt > 0 && room_cnt > 0) result = true;

//				System.out.println(insert_cnt+ " , " + room_cnt);
				instance.close(null, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public ArrayList<TicketVO> selectAllTicket() {
			ArrayList<TicketVO> list = new ArrayList<>();
			try {
				Connection conn = instance.getConnection();
				String sql = "SELECT ct.id, to_char(ct.bookDate, 'mm/dd') 예매한날짜, ct.seatNo 예매좌석, st.roomNo, to_char(st.runDay, 'mm/dd') 상영날짜, " + 
						"mt.movieNo, mt.movieName, to_char(st.runDay, 'hh24:mi') 영화시간, mt.img " + 
						"FROM movie mt, schedule st, ticket ct " + 
						"WHERE mt.movieNo = st.movieNo and st.schNo = ct.schNo";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					TicketVO t = new TicketVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
							rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
					list.add(t);
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public ArrayList<TicketVO> selectMemberTicket(HttpServletRequest request) {
			ArrayList<TicketVO> list = new ArrayList<>();
			try {
				Connection conn = instance.getConnection();
				
				HttpSession httpSession = request.getSession(true);
				MemberVO user = (MemberVO) httpSession.getAttribute("USER");
				if(user == null) return null;
				
				System.out.println(user.getId());
				String sql = "SELECT ct.id, to_char(ct.bookDate, 'mm/dd') 예매한날짜, ct.seatNo 예매좌석, st.roomNo, to_char(st.runDay, 'mm/dd') 상영날짜, " + 
						"mt.movieNo, mt.movieName, to_char(st.runDay, 'hh24:mi') 영화시간, mt.img " + 
						"FROM movie mt, schedule st, ticket ct " + 
						"WHERE mt.movieNo = st.movieNo and st.schNo = ct.schNo and ct.id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					TicketVO t = new TicketVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
							rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
					list.add(t);
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
			// rs -> pstmt -> conn 순으로 close()한다.
			// ===> 객체를 생성한 순서의 반대로 close
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
}
