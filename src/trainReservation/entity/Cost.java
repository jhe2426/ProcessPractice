package trainReservation.entity;

//비용 Entity class : 읽기 전용으로만 사용함(setter는 쓰지 말자고 규칙이 정해져 있음)
//Entity : 데이터베이스 테이블을 클래스로 생성한 것
public class Cost {
	private String departureStation; // 출발 역
	private String arrivalStation; // 도착 역
	private int amount; // 금액

	public Cost() {}

	public Cost(String departureStation, String arrivalStation, int amount) {
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.amount = amount;
	}

	public String getDepartureStation() {
		return this.departureStation;
	}

	public String getArrivalStation() {
		return this.arrivalStation;
	}

	public int getAmount() {
		return this.amount;
	}

	public String toString() {
		return "Cost [departureStation: " + this.departureStation + ", arrivalStation: " + this.arrivalStation
				+ ", amount: " + this.amount + "]";
	}

}
