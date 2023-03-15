package trainReservation.entity;
//기차 정보 Entity class

import java.util.List;

public class Train {
	private String trainNumber;// 기차번호
	private String departureStation;// 출발 역
	private String departureTime;// 출발 시간
	private String arrivalStation;// 도착 역
	private String arrivalTime;// 도착 시간
	private int takeMinute; // 걸리는 분 시간
	private String type; // 기차 종류
	private List<StopStation> stopStations;
	private List<Seat> seats;

	// 비용 (class - 출발 역, 도착 역, 금액) Train 클래스와 종속적 X (그래서 따로 뺌)
	// 정차역 (class - 역 명, 도착 시간, 출발 시간) Train 클래스와 종속적 O
	// 남아있는 좌석 (class - 호차, 좌석 번호, 좌석 상태) Train 클래스와 종속적 O
	public Train() {}

	public Train(String trainNumber, String departureStation, String departureTime, String arrivalStation,
			String arrivalTime, int takeMinute, String type, List<StopStation> stopStations, List<Seat> seats) {
		this.trainNumber = trainNumber;
		this.departureStation = departureStation;
		this.departureTime = departureTime;
		this.arrivalStation = arrivalStation;
		this.arrivalTime = arrivalTime;
		this.takeMinute = takeMinute;
		this.type = type;
		this.stopStations = stopStations;
		this.seats = seats;
	}

	public String getTrainNumber() {
		return trainNumber;
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

	public int getTakeMinute() {
		return takeMinute;
	}

	public String getType() {
		return type;
	}

	public List<StopStation> getStopStations() {
		return stopStations;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	@Override
	public String toString() {
		return "Train [trainNumber=" + trainNumber + ", departureStation=" + departureStation + ", departureTime="
				+ departureTime + ", arrivalStation=" + arrivalStation + ", arrivalTime=" + arrivalTime
				+ ", takeMinute=" + takeMinute + ", type=" + type + ", stopStations=" + stopStations + ", seats="
				+ seats + "]";
	}

}
