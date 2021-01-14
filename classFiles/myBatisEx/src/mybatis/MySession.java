package mybatis;

import java.io.IOException;
import java.io.Reader;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySession {
	// sqlsession 객체를 얻기 위해서 sqlsessionFactory 객체를 선언
	static SqlSessionFactory factory;
	// 정적 : 메모리에 맨 처음에 생성되는 애들
	static {
		Reader r =null; // 파일 읽어오기
		try {
			r = Resources.getResourceAsReader("mybatis/sqlConfig.xml");
			SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder(); // 오라클 공장만들기
			factory = build.build(r); // 오라클 공장 만들어진거 넣어주기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession() {
		return factory.openSession(); // 오라클 공장에 싱글톤으로 만들어준 sql 객체를 넣어준다.
	}
}
