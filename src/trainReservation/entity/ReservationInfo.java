package trainReservation.entity;

import java.util.List;
import java.util.UUID;
//DTO는 getter setter가 다 있는 형태
//Entity는 getter 생성자가 있는 형태 setter가 필요할 때는 사용해도 됨 인스턴스가 한 번 생성되면 변형이 되면 좋지는 않기 때문에 setter가 없는것 필요하면 사용해도 상관은 없음 
//예약한 정보를 저장하는 Entity 클래스
//예약번호
//열차번호
//좌석 번호 리스트
//출발역
//출발 시간
//도착역
//도착 시간
//총 금액
public class ReservationInfo {
	
	private String reservationNumber;//예약 번호
	private String trainNumber;//열차 번호
	private List<String> seats; //좌석 번호 리스트 //여기에 String이 아니라 객체가 들어가게됨 호차랑 좌석번호를 담고 있는 객체를
	private String departureStation;//출발역
	private String departureTime;//출발시간
	private String arrivalStation;//도착역
	private String arrivalTime;//도착시간
	private int totalCost;//총금액
	
	public ReservationInfo() {}

	public ReservationInfo(String trainNumber, List<String> seats, String departureStation,
			String departureTime, String arrivalStation, String arrivalTime, int totalCost) {
		this.reservationNumber = UUID.randomUUID().toString(); //외부에 존재하는 것이 아니기 때문에 외부에서 받아오는 것이 아니라 생성사에서 생성되도록 함
		this.trainNumber = trainNumber; 
		this.seats = seats; 
		this.departureStation = departureStation;
		this.departureTime = departureTime;
		this.arrivalStation = arrivalStation;
		this.arrivalTime = arrivalTime;
		this.totalCost = totalCost; 
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public List<String> getSeats() {
		return seats;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public int getTotalCost() {
		return totalCost;
	}

	@Override
	public String toString() {
		return "ReservationInfo [reservationNumber=" + reservationNumber + ", trainNumber=" + trainNumber + ", seats="
				+ seats + ", departureStation=" + departureStation + ", departureTime=" + departureTime
				+ ", arrivalStation=" + arrivalStation + ", arrivalTime=" + arrivalTime + ", totalCost=" + totalCost
				+ "]";
	}
	
	
	
}
