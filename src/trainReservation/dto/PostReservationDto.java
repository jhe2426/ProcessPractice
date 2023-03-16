package trainReservation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//DTO : Data Transfer Object
public class PostReservationDto {
	
	private String trainNumber; //호차
	private List<String> seats; //좌석
	
	public PostReservationDto(int numberOfPeople) {
		Scanner scanner = new Scanner(System.in);
		this.seats = new ArrayList<String>();
		
		while (true) {
	 		System.out.println("탑승할 열차 번호 : ");
			this.trainNumber = scanner.nextLine();
			
			if (this.trainNumber.isBlank()) {
				System.out.println("열차 번호를 입력하세요.");
				continue;
			} 
			break;
		}

		while (this.seats.size() < numberOfPeople) { //처음에 this.seats.size()가 0으로 시작하니깐 < numberOfPeople임 numberOfPeople수 만큼 돌려면
			System.out.println("좌석 번호 : ");
			String seat = scanner.nextLine();
			if (seat.isBlank()) {
				System.out.println("좌석 번호를 입력하세요.");
				continue;
			} 
			this.seats.add(seat);
		}
		//입력 받고 검증까지 완료!!
	}
	
	public PostReservationDto(String trainNumber, List<String> seats) {
		this.trainNumber = trainNumber;
		this.seats = seats;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public List<String> getSeats() {
		return seats;
	}

	
	@Override
	public String toString() {
		return "PostReservationDto [trainNumber=" + trainNumber + ", steats=" + seats + "]";
	}
	
	public boolean isEqualTrainNumber(String trainNumber) {
		return this.trainNumber.equals(trainNumber);
	}
	
	
}
