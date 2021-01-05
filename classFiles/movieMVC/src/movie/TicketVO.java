package movie;

import java.sql.Date;

public class TicketVO {
	private String id; // 회원 아이디
	private String bookDay;// 예매한 날짜
	private int seatNo; // 예매 좌석 번호
	private int roomNo; // 상영관번호
	private String runDay; // 상영날짜
	private int movieNo; // 영화 번호
	private String movieName; //영화제목
	private String runtime; // 영화시간
	private String img; // 영화 사진
	
	
	public TicketVO(String id, String bookDay, int seatNo, int roomNo, String runDay, int movieNo, String movieName,
			String runtime, String img) { 
		this.id = id;
		this.bookDay = bookDay;
		this.seatNo = seatNo;
		this.roomNo = roomNo;
		this.runDay = runDay;
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.runtime = runtime;
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookDay() {
		return bookDay;
	}
	public void setBookDay(String bookDay) {
		this.bookDay = bookDay;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRunDay() {
		return runDay;
	}
	public void setRunDay(String runDay) {
		this.runDay = runDay;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "TicketVO [id=" + id + ", bookDay=" + bookDay + ", seatNo=" + seatNo + ", roomNo=" + roomNo
				+ ", runDay=" + runDay + ", movieNo=" + movieNo + ", movieName=" + movieName + ", runtime=" + runtime
				+ ", img=" + img + "]";
	}
	
	
	
	
}
