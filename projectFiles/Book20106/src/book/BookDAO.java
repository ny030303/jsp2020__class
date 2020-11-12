package book;

import java.sql.*;
import java.util.ArrayList;

//import member.MemberVO;


public class BookDAO {
	
	// 싱글톤 패턴 => 하나의 인스턴스를 만들고 그걸 계속 불러 사용함. => 그 역할이 instance
		private static BookDAO instance = new BookDAO();
		
		// BookVO 생성자	
		private BookDAO() { }
		
		// private인 instance를 불러 쓸 수 있게 해줄 getInstance()
		public static BookDAO getInstance() {
			return instance;
		}

	// - 오라클 커넥션 객체생성
		public Connection getConnection() {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
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
		
		public int createBcode() {
//			 초기값 bcode
			int bcode = 0;
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();
				// BOOK_TBL 에서  BCODE 최대값을 가져오는 sql
				String getNoSql = "SELECT MAX(BCODE) FROM BOOK_TBL";
				
				// PreparedStatement => statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
				// Connection 객체의 prepareStatement(String query)를 통해 생성
				PreparedStatement pstmt = conn.prepareStatement(getNoSql);
				// sql 문을 실행
				ResultSet rs = pstmt.executeQuery();
				// rs 객체는 포인터 개념으로 시작인 BOF 다음부터 EOF 이전까지 레코드를 1개씩 가져오기
				if (rs.next()) {
					// bcode에 +1해주기
					// ex) rs.getInt(1) ==> 10106
					//     10106 + 1 = 10107
					//     bcode = 10107
					bcode = rs.getInt(1) + 1;
				}
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bcode;
		}
		
		public int insertBook(BookVO vo) {
			// 초기값 설정
			int cnt = -1;
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();
				// 최대값 가져오기
				
				String insertSql = "insert into book_tbl(bcode,btitle,bwriter,bpub,bprice,bdate) values(?,?,?,  ?,?,?)";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(insertSql);
				// select query만 executeQuery(), insert, delete, update는 executeUpdate()
				// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
				pstmt.setInt(1, vo.getBcode());
				pstmt.setString(2, vo.getBtitle());
				pstmt.setString(3, vo.getBwriter());
				pstmt.setInt(4, vo.getBpub());
				pstmt.setInt(5, vo.getBprice());
				pstmt.setDate(6, vo.getBdate());
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
		
		public ArrayList<BookVO> selectAllBooks() {
			// 리스트 생성
			ArrayList<BookVO> list = new ArrayList<BookVO>();
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();
				String selectSql = "SELECT * FROM BOOK_TBL ORDER BY BCODE ASC";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(selectSql);
				// select query만 executeQuery(), insert, delete, update는 executeUpdate()
				// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) { // 1줄씩 가져옴.
					int bcode = rs.getInt("bcode");
					String btitle = rs.getString("btitle");
					String bwriter = rs.getString("bwriter");
					int bpub = rs.getInt("bpub");
					int bprice = rs.getInt("bprice");
					Date bdate = rs.getDate("bdate");
					
					// BookVO 객체 생성 후 값 넣어주기
					BookVO vo = new BookVO(bcode, btitle, bwriter, bpub, bprice, bdate);
					
					// 리스트에 넣기
					list.add(vo);
				}
				// 객체를 생성한 순서의 반대로 close
				instance.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// member 정보가 담긴  ArrayList인 list 반환
			return list;
		}
		
		public int deleteBook(int bcode) {
			// 초기값 설정
			int cnt = 0;
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();
				String deleteSql = "DELETE FROM BOOK_TBL WHERE BCODE = ?";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(deleteSql);
				// select query만 executeQuery(), insert, delete, update는 executeUpdate()
				// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
				pstmt.setInt(1, bcode);
				// delete 라서 executeUpdate()
				cnt = pstmt.executeUpdate();
				// 객체를 생성한 순서의 반대로 close
				instance.close(null, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// row 개수 리턴
			return cnt;
		}
		
		public BookVO getABook(int bcode) {
			// MemberVO 인 vo 생성
			BookVO vo = new BookVO(0,null,null,0,0,null);
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();
				String selectSql = "SELECT * FROM BOOK_TBL WHERE BCODE = ?";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(selectSql);
				// select query만 executeQuery(), insert, delete, update는 executeUpdate()
				// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
				pstmt.setInt(1, bcode);
				// select 라서 executeQuery()
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) { // 1줄씩 가져옴.
					// vo에 각각 값을 set
					vo.setBcode(rs.getInt("bcode"));
					vo.setBtitle(rs.getString("btitle"));
					vo.setBwriter(rs.getString("bwriter"));
					vo.setBpub(rs.getInt("bpub"));
					vo.setBprice(rs.getInt("bprice"));
					vo.setBdate(rs.getDate("bdate"));
				}
				// 객체를 생성한 순서의 반대로 close
				instance.close(null, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 만들어진 vo를 반환
			return vo;
		}

		public int updateBook(BookVO vo) {
			// 초기값 설정
			int cnt = -1;
			try {
				// 커넥션 객체 가져오기
				Connection conn = instance.getConnection();

				String updateSql = "UPDATE BOOK_TBL SET BCODE=?, Btitle=?, bwriter=?, bpub=?, bprice=?, bdate=? WHERE bcode = ?";
				// sql 실행하기 위한 객체 실행문
				PreparedStatement pstmt = conn.prepareStatement(updateSql);
				// select query만 executeQuery(), insert, delete, update는 executeUpdate()
				// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
				pstmt.setInt(1, vo.getBcode());
				pstmt.setString(2, vo.getBtitle());
				pstmt.setString(3, vo.getBwriter());
				pstmt.setInt(4, vo.getBpub());
				pstmt.setInt(5, vo.getBprice());
				pstmt.setDate(6, vo.getBdate());
				pstmt.setInt(7, vo.getBcode());
				// update 라서 executeUpdate()
				cnt = pstmt.executeUpdate();
				// 객체를 생성한 순서의 반대로 close
				instance.close(null, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// row 개수 리턴
			return cnt;
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
