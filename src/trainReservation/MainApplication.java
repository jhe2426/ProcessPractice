package trainReservation;

import trainReservation.controller.ReservationController;

public class MainApplication {

	private static ReservationController reservationController = new ReservationController();

	public static void main(String[] args) {
		//호차 선택하는 부분을 빼버림 입력 받을 때 룸 번호를 받으면 되고 
		reservationController.reservation();

	}

}
