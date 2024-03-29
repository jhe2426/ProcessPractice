package trainReservation.entity;

//좌석 Entity class
//Train 클래스와 Seat 클래스는 1 : n 관계임
public class Seat {
	private int roomNumber; // 기차 호차
	private String seatNumber;// 좌석 번호
	private boolean seatStatus;// 좌석 상태

	public Seat() {}

	public Seat(int roomNumber, String seatNumber, boolean seatStatus) {
		this.roomNumber = roomNumber;
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public boolean isSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(boolean seatStatus) {
		this.seatStatus = seatStatus;
	}

	@Override
	public String toString() {
		return "Seat [roomNumber=" + roomNumber + ", seatNumber=" + seatNumber + ", seatStatus=" + seatStatus + "]";
	}

}
