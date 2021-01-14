package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.ibatis.session.SqlSession;

import model.MemoVO;
import mybatis.MySession;


public class MemoDAO {
	private static MemoDAO instance = new MemoDAO();
	private MemoDAO(){};
	public static MemoDAO getInstance(){
		return instance;
	}
	//=================================================
	public int insert(MemoVO m){
		SqlSession mapper = MySession.getSession();
		int cnt = mapper.insert("insertMemo", m);
		mapper.commit();
		mapper.close();
		return cnt;
	}
	public int getCount(){
		SqlSession session  = MySession.getSession();
		int cnt = (Integer)session.selectOne("getCount");
		session.close();
		return cnt;
	}
	public MemoVO selectByIdx(int idx){
		SqlSession session  = MySession.getSession();
		MemoVO m = (MemoVO)session.selectOne("selectByIdx",idx);
		session.close();
		return m;
	}
}
