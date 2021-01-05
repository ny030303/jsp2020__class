package movie;

public class RoomVO {
	private int roomNo; // 상영관 번호
	private int schNo; // 스케줄 번호
	private int seatCnt; // 상영관 내에 예매된 좌석 카운트
	
	
	public RoomVO(int roomNo, int schNo, int seatCnt) {
		this.roomNo = roomNo;
		this.schNo = schNo;
		this.seatCnt = seatCnt;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getSchNo() {
		return schNo;
	}
	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}
	public int getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}
	@Override
	public String toString() {
		return "RoomVO [roomNo=" + roomNo + ", schNo=" + schNo + ", seatCnt=" + seatCnt + "]";
	}
	
	
	
}
