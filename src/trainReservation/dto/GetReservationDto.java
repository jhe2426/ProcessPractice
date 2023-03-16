package trainReservation.dto;

import java.util.Scanner;

//무조건 레이어 간의 데이터 전달은 변수가 1개라도 따로 DTO클래스로 관리하는 것이 유지보수에도 좋고 관리하기에도 너무 좋다.
public class GetReservationDto {
	private String reservationNumber;//예약 번호
	
	public GetReservationDto() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("예약 번호 : ");
		this.reservationNumber = scanner.nextLine();
	}
	
	public GetReservationDto(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	@Override
	public String toString() {
		return "GetReservationDto [reservationNumber=" + reservationNumber + "]";
	}	
	
}
