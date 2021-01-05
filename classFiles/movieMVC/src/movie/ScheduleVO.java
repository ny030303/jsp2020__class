package movie;

public class ScheduleVO {
	private int schNo; 
	private String movieName;
	private String category;
	private int runtime;
	private String img;
	private String info;
	private String runDay;
	private String time;
	private int roomNo;
	
	
	
	
	
	public ScheduleVO(int schNo, String movieName, String category, int runtime, String img, String info, String runDay,
			String time, int roomNo) {
		this.schNo = schNo;
		this.movieName = movieName;
		this.category = category;
		this.runtime = runtime;
		this.img = img;
		this.info = info;
		this.runDay = runDay;
		this.time = time;
		this.roomNo = roomNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSchNo() {
		return schNo;
	}
	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRunDay() {
		return runDay;
	}
	public void setRunDay(String runDay) {
		this.runDay = runDay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	@Override
	public String toString() {
		return "ScheduleVO [schNo=" + schNo + ", movieName=" + movieName + ", category=" + category + ", runtime="
				+ runtime + ", img=" + img + ", info=" + info + ", runDay=" + runDay + ", time=" + time + ", roomNo="
				+ roomNo + "]";
	}
	
	
	
}
