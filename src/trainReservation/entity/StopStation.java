package trainReservation.entity;
//정차역 Entity class
//Train과 StopStation은 1 : N 관계임(그래서 train클래스에 StopStation 타입을 받는 List를 선언 해놓음)
public class StopStation {
	private String stationName; //역 이름
	private String departureTime; //출발 시간
	private String arrivalTime; //도착 시간
	
	public StopStation() {}

	public StopStation(String stationName, String departureTime, String arrivalTime) {
		this.stationName = stationName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public String getStationName() {
		return stationName;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public String toString() {
		return "StopStation [stationName=" + stationName + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}
	
	
}
