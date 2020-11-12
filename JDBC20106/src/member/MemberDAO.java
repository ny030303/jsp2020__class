package member;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	// 싱글톤 패턴 => 하나의 인스턴스를 만들고 그걸 계속 불러 사용함. => 그 역할이 instance
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
	}
	
	// private인 instance를 불러 쓸 수 있게 해줄 getInstance()
	public static MemberDAO getInstance() {
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

	// - 회원번호를 1씩 증가시키기 위한 테이블의 memno의 최댓값 리턴(5점)
	public int getMaxNo() {
		// 초기값 memno
		int memno = 0;
		try {
			// 커넥션 객체 가져오기
			Connection conn = instance.getConnection();
			// MEMBER_TBL 에서 MEMNO 최대값을 가져오는 sql
			String getNoSql = "SELECT MAX(MEMNO) FROM MEMBER_TBL";
			
			// PreparedStatement => statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
			// Connection 객체의 prepareStatement(String query)를 통해 생성
			PreparedStatement pstmt = conn.prepareStatement(getNoSql);
			// sql 문을 실행
			ResultSet rs = pstmt.executeQuery();
			// rs 객체는 포인터 개념으로 시작인 BOF 다음부터 EOF 이전까지 레코드를 1개씩 가져오기
			if (rs.next()) {
				memno = rs.getInt(1) + 1;
			}
			instance.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memno;
	}

	// - selectSql 실행 전체 목록(5점)
	public ArrayList<MemberVO> selectMembers() {
		// 리스트 생성
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			Connection conn = instance.getConnection();
			String selectSql = "SELECT * FROM MEMBER_TBL ORDER BY MEMNO";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // 1줄씩 가져옴.
				MemberVO user = new MemberVO();
				
				// 해당 컬럼명으로 MemberVO인 user에 set 하기
				user.setMemno(rs.getInt("MEMNO"));
				user.setName(rs.getString("NAME"));
				user.setId(rs.getString("ID"));
				user.setPass(rs.getString("PASS"));
				user.setBirth(rs.getInt("BIRTH"));
				user.setGender(rs.getString("GENDER"));
				user.setJob(rs.getString("JOB"));
				user.setCity(rs.getString("CITY"));
				user.setJoinDate(rs.getDate("JOINDATE"));
				// 리스트에 넣기
				list.add(user);
			}
			// 객체를 생성한 순서의 반대로 close
			instance.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// member 정보가 담긴  ArrayList인 list 반환
		return list;
	}

	// - insertSql 실행 – 성공시 row 개수 리턴(5점)
	public int insertMember(MemberVO vo) {
		// 초기값 설정
		int cnt = -1;
		try {
			Connection conn = instance.getConnection();
			// 현재 가장 큰  memno 가져오기 (최대값)
			int memno = instance.getMaxNo();
			
			String insertSql = "INSERT INTO MEMBER_TBL(memno, name, id, pass, birth, gender, job, city, joindate) "
					+ "VALUES(?,?,?,?, ?,?,?,?,SYSDATE)";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			pstmt.setInt(1, memno);
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getPass());
			pstmt.setInt(5, vo.getBirth());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getJob());
			pstmt.setString(8, vo.getCity());
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

	// - updateSql 실행 - – 성공시 row 개수 리턴(5점)
	public int updateMember(MemberVO vo) {
		// 초기값 설정
		int cnt = -1;
		try {
			Connection conn = instance.getConnection();

			String updateSql = "UPDATE MEMBER_TBL SET pass=?, birth=?, gender=?, job=?, city=? WHERE memno = ?";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			pstmt.setString(1, vo.getPass());
			pstmt.setInt(2, vo.getBirth());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getJob());
			pstmt.setString(5, vo.getCity());
			pstmt.setInt(6, vo.getMemno());
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

	// - deleteSql 실행 – 성공시 row 개수 리턴(5점)
	public int deleteMember(int memno) {
		// 초기값 설정
		int cnt = 0;
		try {
			Connection conn = instance.getConnection();
			String deleteSql = "DELETE FROM MEMBER_TBL WHERE MEMNO = ?";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(deleteSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			pstmt.setInt(1, memno);
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

	// - selectSql(memno 에 해당하는 자료 1건)(5점)
	public MemberVO getAMember(int memno) {
		// MemberVO 인 vo 생성
		MemberVO vo = new MemberVO();
		try {
			Connection conn = instance.getConnection();
			
			String selectSql = "SELECT * FROM MEMBER_TBL WHERE memno = ?";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			pstmt.setInt(1, memno);
			// select 라서 executeQuery()
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 1줄씩 가져옴.
				// vo에 각각 값을 set
				vo.setMemno(rs.getInt("memno"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));

				vo.setBirth(rs.getInt("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setJob(rs.getString("job"));
				vo.setCity(rs.getString("city"));
				vo.setJoinDate(rs.getDate("joinDate"));
			}
			// 객체를 생성한 순서의 반대로 close
			instance.close(null, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 만들어진 vo를 반환
		return vo;
	}
	
	// oracle이 아닌 mysql 일때 Connection 연결
	public Connection getMysqlConnection() {
		String url = "jdbc:mysql://localhost:3306/mysqldb";
		String user = "root";
		String pw = "root";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // mysql 드라이버 로딩
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("mysql 접속성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
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

	// - idCheck.jsp의 checkSql 실행(5점)
	public boolean idAvailableChk(String id) {
		// 초기값 설정
		boolean result = false;
		try {
			Connection conn = instance.getConnection();
			String checkSql = "SELECT * FROM MEMBER_TBL WHERE id = ?";
			// sql 실행하기 위한 객체 실행문
			PreparedStatement pstmt = conn.prepareStatement(checkSql);
			// select query만 executeQuery(), insert, delete, update는 executeUpdate()
			// select query만 결과를 set(집합)으로, 나머지 query는 성공한 row의 갯수 (int)로 반환
			pstmt.setString(1, id);
			//select라서 executeQuery()
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) 
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 결과 값에 따라 바뀌는 result 반환
		return result;
	}
}
