package board;

public class BoardVO {
	// 멤버변수
	private int boardNum;
	private String name;
	private String pass;
	private String title;
	private String content;
	private String writeDate;
	
	// 생성자
	public BoardVO(int boardNum, String name, String pass, String title, String content, String writeDate) {
		this.boardNum = boardNum;
		this.name = name;
		this.pass = pass;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
	}
	
	public BoardVO() {
		
	}

	// get,set
	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	// toString
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", name=" + name + ", pass=" + pass + ", title=" + title + ", content="
				+ content + ", writeDate=" + writeDate + "]";
	}
}
