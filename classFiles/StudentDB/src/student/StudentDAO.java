package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
	// DAO : 기능구현하는 클래스
	private static StudentDAO instance = new StudentDAO();
	private StudentDAO() {}
	// studentVO == 데이터베이스에서 데이터를 가지고 있는 객체를 만들어주는 클래스
	// studentVO => 어떤 데이터 => 테이블에서 한 열 == row == 행 == 레코드
	// ArrayList<StudentVO> list ==> 여러 row 이루어진 결국 => 데이터베이스에 테이블로 구성된다.
	
	public static StudentDAO getInstance() {
		return instance;
	}
	
	// DB
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// drive
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "hr";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch(Exception e) {
			e.printStackTrace();
		} finally { // 오류가 발생하던 안하던 무조건 실행
			
		}
		
		return conn;
	}
	
	// 학생 전체목록 출력
	public ArrayList<StudentVO> getStudentList() {
		ArrayList<StudentVO> studentlist = new ArrayList<StudentVO>();
		try {
			// 오라클 연동하는 객체들
			conn = instance.getConnection(); // 오라클
			String sql = "select * from STUDENT_TBL"; // sql 쿼리스트링
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 그 다음 실행문이 있니??
				// rs.next()는 한줄을 가져온다.
				StudentVO student = new StudentVO();
				// student.setStuNo(rs.getInt(1)); // 컬럼 순서
				student.setStuNo(rs.getInt("stuNo")); // 칼럼 이름  // 학번
				student.setPassword(rs.getString("password")); // 비번
				student.setStuName(rs.getString("stuName")); // 이름
				student.setGrade(rs.getInt("grade"));
				student.setKor(rs.getInt("kor"));
				student.setMath(rs.getInt("math"));
				student.setEng(rs.getInt("eng"));
				
				studentlist.add(student);
			}
			System.out.println("학생 출력 완료");
		}catch (Exception e) {
			// try안에 있는 코드의 오류를 찾아서
			System.out.println("getStudentList() 오류");
			e.printStackTrace(); // 에러 콘솔 창에 출력
		} finally {
			// 오류 상관 없이 무조건 실행하는 부분
			try {
				if (conn != null) {conn.close();}
				if (pstmt != null) {pstmt.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return studentlist;
	}
	
	// insertStudent : 외부 : form input 받아온 것을 오라클에 넣어주는 기능
	// 굳이 리턴 필요없지만 boolean 리턴을 받는다. --> insert성공하면 오라클에 성공적으로 삽입되면 true반환
	public boolean insertStudent(StudentVO vo) {
		try {
			conn = instance.getConnection();
			String sql = "INSERT into STUDENT_TBL values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); // ? 넣어주는
			// number -> setInt varchar2 -> setString
			// insertStudent.jsp -> form input 정보 ->
			// insertStudentPro.jsp -> StudentVO 생성 -> input정보를 set을 통해서 넣어주고
			// StudentDAO에서 insertStudent 매서드의 외부인자 : studentVO의 객체를 받아오고
			// PreparedStatement에 있는 ?에 set해서 값 넣을 때 -> StudentVO객체에서 get을 통해서 넣어준다
			pstmt.setInt(1, vo.getStuNo()); // ?에 stuNo넣어준다
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getStuName());
			pstmt.setInt(4, vo.getGrade());
			pstmt.setInt(5, vo.getKor());
			pstmt.setInt(6, vo.getMath());
			pstmt.setInt(7, vo.getEng());	
			pstmt.executeUpdate();
			System.out.println("학생 한명 추가 완료");
			return true;
		} catch (Exception e) {
			System.out.println("insertStudent() 오류");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (pstmt != null) {pstmt.close();}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return false;
	}
	
	// 학생 한명 정보 출력
	public StudentVO getStudent(int stuNo) {
		StudentVO student = new StudentVO();
		
		try {
			conn = instance.getConnection(); // 오라클 연동
			String sql = "select * from STUDENT_TBL where stuNo = ? ";
			pstmt = conn.prepareStatement(sql); // ? 넣어주는
			pstmt.setInt(1, stuNo); // ?에 stuNo넣어준다
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				student.setStuNo(rs.getInt("stuNo")); // 칼럼 이름  // 학번
				student.setPassword(rs.getString("password")); // 비번
				student.setStuName(rs.getString("stuName")); // 이름
				student.setGrade(rs.getInt("grade"));
				student.setKor(rs.getInt("kor"));
				student.setMath(rs.getInt("math"));
				student.setEng(rs.getInt("eng"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (pstmt != null) {pstmt.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return student;
	}
	
}
