package trainReservation.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import trainReservation.dto.GetReservationDto;
import trainReservation.dto.GetTrainListDto;
import trainReservation.dto.PostReservationDto;
import trainReservation.entity.ReservationInfo;
import trainReservation.entity.Train;
import trainReservation.service.ReservationService;

//Controller class (계층)
//사용자로부터 입력받는 기능을 수행하고 / 또 입력 받은 데이터를 검증하는 기능을 담당 / 또 비즈니스 로직의 결과를 반환해주는 역할을 한다.
public class ReservationController {

	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	private ReservationService reservationService = new ReservationService();

	private GetReservationDto getReservationDto;
	private GetTrainListDto getTrainListDto;
	private PostReservationDto postReservationDto;
	
	public ReservationController() {
		this.reservationService = new ReservationService();
	}

	public void reservation() {

		while(true) {
			// 참조 변수 이름에 get으로 시작하면 안 됨 메서드와 헷갈릴 수 도 있기 때문에 getTrainListDto
			getTrainListDto = new GetTrainListDto(); //UI가 있을 때는 UI에서 값을 받아와야지 이런식으로 하면 안 된다고 함 //여기에 선언하는 것이 맞긴 함 근데 위에 지역 변수로 선언을 함

			LocalTime departureTime = null;

			// 모든 값을 입력했는지에 대한 검증
			if (getTrainListDto.isEmpty()) {
				System.out.println("모두 입력하세요.");
				continue;
			}

			// 입력한 시간이 올바른지
			try {// LocalTime.parse() : String을 LocalTime으로 만들어주는 메서드 //timeFormatter형식으로 입력 받은
					// 값을 포맷하도록 해주는 메서드
				departureTime = LocalTime.parse(getTrainListDto.getDepartureTime(), timeFormatter);
			} catch (Exception exception) {
				System.out.println("잘못된 시간입니다.");
				continue;
			}

			// 입력한 인원이 올바른지에 대한 검증
			if (getTrainListDto.getNumberOfPeople() <= 0) {
				System.out.println("잘못된 인원입니다.");
				continue;
			}

			// 입력한 출발역과 도착역이 같은지에 대한 검증
			if (getTrainListDto.isEqualStation()) {
				System.out.println("출발역과 도착역이 같습니다.");
				continue;
			}
			// 여기까지가 입력받은 거 올바르게 입력했는지 검증 받는 코드였음

			List<Train> possibleTrains = reservationService.getPossibleTrainList(getTrainListDto, departureTime);
			System.out.println(possibleTrains.toString());

			postReservation(); //기능 단위로 분리해서 메서드로 만들어 놓으면 훨씬 코드가 깔끔해짐
			break;
		}
		
	}
	
	public void postReservation() {
		while(true) {
			//출발역과 도착역을 입력받는 부분이 위에서 한 번만 밖기 때문에 또 다시 밖기에는 그래서 또 costTotal값 또한 계산해서 얻기위한 정보들 중에서 필요한 것이 있어
			//PostReservationDto이거를 같이 선언한 것 원래는 GetTrainListDto의 입력을 검증받는 것과 PostReservationDto을 분리해줘야함
			postReservationDto = new PostReservationDto(getTrainListDto.getNumberOfPeople()); //UI가 있을 때는 UI에서 값을 받아와야지 이런식으로 하면 안 된다고 함
			
			ReservationInfo reservationInfo = reservationService.postReservation(postReservationDto, getTrainListDto);
			if (reservationInfo == null) continue;
			
			System.out.println(reservationInfo.toString());
			break; //이 break문을 만나게 되면 이제 프로그램이 종료가 됨
		}
		
	}
	//데이터가 하나이더라도 클래스로 관리하는 것이 아주 편하고 유지보수에 좋다.
	public void getReservation() { 
		
		while(true) {
			getReservationDto  = new GetReservationDto();
			String reservationNumber = getReservationDto.getReservationNumber();
			if (reservationNumber.isBlank()) {
				System.out.println("예약번호를 입력하세요.");
				continue;
			}
			//외부에서 값을 가져올 때도 무조건 검증을 해주어야 한다.
			//늘 인스턴스를 생성해서 리턴해준다는 보장이 없고 null이 리턴이 되어 reservationInfo 변수에 값이 들어갈 수 도 있기 때문에
			//이러한 것도 예측을 해주어 예외를 처리를 해줘야하는 것이다.
			ReservationInfo reservationInfo = reservationService.getReservation(getReservationDto); 
			
			//실제로는 이렇게 작업을 해야함
//			if (reservationInfo == null) {
//				System.out.println("해당 예약번호의 예약정보가 없습니다.");
//				break;
//			}
//			System.out.println(reservationInfo.toString());
//			break;	
			//삼항연산자 복습을 위해 아래와 같이 사용해본 것이지만 실무에서는 위에 처럼 if문으로 작성해야한다.
			//사용하지 말라는 이유는 같은 문자열이지만, 정상결과에 대한 반환, 에러 메세지
			//즉. 정확한 결과와 예러 메세지 이므로 서로 다른의미를 가지고 있으므로 이렇게 사용하지 않는 것
			String message = 
					reservationInfo == null ? "해당하는 예약번호가 없습니다."
							: reservationInfo.toString();
			System.out.println(message);
			break;	
		}
		
	}

}
