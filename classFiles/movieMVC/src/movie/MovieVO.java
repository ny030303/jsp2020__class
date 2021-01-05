package movie;

public class MovieVO {
	private int movieNo; // 영화 번호
	private String movieName;// 영화  제목
	private int category; //장르
	private int runtime;//120분
	private String img; // 이미지 파일 불러오기 위한 제목
	private String info; // 영화정보
	
	
	public MovieVO(int movieNo, String movieName, int category, int runtime, String img, String info) {
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.category = category;
		this.runtime = runtime;
		this.img = img;
		this.info = info;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	@Override
	public String toString() {
		return "MovieVO [movieNo=" + movieNo + ", movieName=" + movieName + ", category=" + category + ", runtime="
				+ runtime + ", img=" + img + ", info=" + info + "]";
	}
	
	
}
