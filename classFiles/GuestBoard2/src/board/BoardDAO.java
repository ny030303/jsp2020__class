package board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardDAO {
	// 다 암기해
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	
	public ArrayList<BoardVO> selectAllBoards() {
		System.out.println("게시글 출력 완료");
		return list;
	}
	
	public int getBoardNum() {
		int boardNum = 0;
		
		for (int i = 0; i < list.size(); i++) {
			if (boardNum < list.get(i).getBoardNum()) {
				boardNum = list.get(i).getBoardNum();
			}
		}
		
		return boardNum + 1;
	}
	
	public String getDate() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss");
		String writeDate = df.format(today);
		System.out.println("작성 날짜 : " + writeDate);
		return writeDate;
	}
	
	public int insertBoard(BoardVO vo) {
		int check = 1;
		list.add(vo);
		return check;
	}
	
	public void updateBoard(BoardVO vo) {
		int idx = -1;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBoardNum() == vo.getBoardNum()) {
				idx = i;
			}
		}
		
		list.set(idx, vo);
	}
	
	public BoardVO selectABoard(int boardNum) { // update하기 위해 선택된 게시 글을 가져오는 메서드 구현
		int idx = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBoardNum() == boardNum) {
				idx = i;
			}
		}
		
		String name = list.get(idx).getName();
		String pass = list.get(idx).getPass();
		String title = list.get(idx).getTitle();
		String content = list.get(idx).getContent();
		String writeDate = list.get(idx).getWriteDate();
		
		BoardVO vo = new BoardVO(boardNum, name, pass, title, content, writeDate);
		return vo;
	}
}
