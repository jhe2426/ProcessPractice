package trainReservation.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import trainReservation.dto.GetTrainListDto;
import trainReservation.entity.Train;
import trainReservation.service.ReservationService;

//Controller class (계층)
//사용자로부터 입력받는 기능을 수행하고 / 또 입력 받은 데이터를 검증하는 기능을 담당 / 또 비즈니스 로직의 결과를 반환해주는 역할을 한다.
public class ReservationController {
	
		private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		private  ReservationService reservationService = new ReservationService();
	
		public ReservationController() {
				this.reservationService = new  ReservationService();
		}
		public void reservation() {
			
			while (true) {		
				//참조 변수 이름에 get으로 시작하면 안 됨 메서드와 헷갈릴 수 도 있기 때문에 getTrainListDto 
				GetTrainListDto dto = new GetTrainListDto();
				
				LocalTime departureTime = null;
				
				//모든 값을 입력했는지에 대한 검증
				if (dto.isEmpty()) {
					System.out.println("모두 입력하세요.");
					continue;
				}
				
				//입력한 시간이 올바른지
				try {//LocalTime.parse() : String을 LocalTime으로 만들어주는 메서드 //timeFormatter형식으로 입력 받은 값을 포맷하도록 해주는 메서드
						departureTime = LocalTime.parse(dto.getDepartureTime(), timeFormatter);
				} catch(Exception exception) {
					System.out.println("잘못된 시간입니다.");
					continue;
				}
				
				
				//입력한 인원이 올바른지에 대한 검증
				if (dto.getNumberOfPeople() <= 0) {
					System.out.println("잘못된 인원입니다.");
					continue;
				}
				
				//입력한 출발역과 도착역이 같은지에 대한 검증
				if (dto.isEqualStation()) {
					System.out.println("출발역과 도착역이 같습니다.");
					continue;
				}
				// 여기까지가 입력받은 거 올바르게 입력했는지 검증 받는 코드였음
				
				
				List<Train> possibleTrains = reservationService.getPossibleTrainList(dto, departureTime);
			
				
				System.out.println(possibleTrains.toString());
				
				
			}
		}
	

}
