package trainReservation;

import java.util.Scanner;

import trainReservation.controller.ReservationController;

public class MainApplication {

	private static ReservationController reservationController = new ReservationController();

	public static void main(String[] args) {
		
		while(true) {		
			Scanner scanner = new Scanner(System.in);
			String endPoint = scanner.nextLine();
			
			if (endPoint.equals("POST /reservation"))
				reservationController.reservation();//호차 선택하는 부분을 빼 버림 입력 받을 때 룸 번호를 받으면 되고 
			
			if (endPoint.equals("GET /reservation"))
				reservationController.getReservation();//기차 번호 검색후 기차 예약 정보 출력해주는 메서드
			
			if (endPoint.equals("END")) break;
		}
		
		
	}

}
