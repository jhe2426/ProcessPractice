package trainReservation.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import trainReservation.dto.GetReservationDto;
import trainReservation.dto.GetTrainListDto;
import trainReservation.dto.PostReservationDto;
import trainReservation.entity.Cost;
import trainReservation.entity.ReservationInfo;
import trainReservation.entity.Seat;
import trainReservation.entity.StopStation;
import trainReservation.entity.Train;

//Service class (계층)
//실제 비즈니스 로직을 담당

public class ReservationService {

	private static List<Train> trains = new ArrayList<Train>();
	private static List<Cost> costs = new ArrayList<Cost>();
	private static List<ReservationInfo> reservations = new ArrayList<ReservationInfo>(); //예약 번호 입력시 예약 정보를 보여주기 위해 예약 정보를 저정할 리스트 생성
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	public ReservationService() {
		initData();
	}

	public List<Train> getPossibleTrainList(GetTrainListDto dto, LocalTime departureTime) {

		// 최종으로 리턴 받을 결과물임 : possibleTrains
		List<Train> possibleTrains = new ArrayList<Train>(); // while문 위쪽에 선언하는 것이 좋긴함

		// 조회만 할 것이라서 foreach문 사용
		for (Train train : trains) { // 해당 index값이 필요하지도, 값의 변경도 있지 않으므로 foreach문으로 사용한 것
			List<StopStation> stopStations = train.getStopStations(); // ??이렇게 해도 왜 오류가 안 나는지 생각해보기
			// List<StopStation> stopStations = new ArrayList<StopStation>();
			// stopStations = train.getStopStations();
			int sameStationIndex = -1;

			for (int stopStationIndex = 0; stopStationIndex < stopStations.size(); stopStationIndex++) { // 인덱스가 필요하므 foreach문을  사용하지 않은 것
				StopStation stopStation = stopStations.get(stopStationIndex);
				String stopStationName = stopStation.getStationName();

				// 출발역의 이름이 정차역이름과 같은지
				if (!dto.isEqualDepartureStation(stopStationName)) { //정차역과 이름이 같은 인덱스는 continue를 만나지 않고 밑에 if문을 만나게 된다.
					continue;
				}
				if (stopStation.getDepartureTime().equals("")) continue; //내가 실습했을 때 발생한 예외가 ""stopStation 시간에 ""에 대한 검증을 안 해줘서 그런 것(여기에서는 오류가 발생하지 않았던 것은 stopStation의 생성자의 매개변수가 바껴있어서 그런 것)
				
				LocalTime stationDepartureTime = LocalTime.parse(stopStation.getDepartureTime(), timeFormatter);//는 timeFormatter의 포맷 형식이 아닌 문자열이 들어오면 바로 예외를 뱉어냄

				// 입력한 시간 이후의 열차가 존재하는지에 대한 구문
				// isBefore() : 지나간 날짜인지 비교하여 boolean 값 반환 stationDepartureTime이 departureTime보다
				//isBefore() : 인자보다 과거일 때 true를 반환
				//왜 출발역의 시간을 기준으로 안하고 정차역의 시간들을 전부 다 비교하는 이유는 무엇인지? 일단 해당 출발역이 어딘인지가 반복문을 통해서 찾아야하므로 
				if (stationDepartureTime.isBefore(departureTime)) { // 시간이 지나면 break을 만나게 됨 
					break;
				}
				sameStationIndex = stopStationIndex;
				break;
			}

			if (sameStationIndex == -1) {
				continue;
			}

			boolean isPossible = false;

			for (int stopStationIndex = 0; stopStationIndex < stopStations.size(); stopStationIndex++) {
				String statioName = stopStations.get(stopStationIndex).getStationName();

				if (!dto.isEqualArrivalStation(statioName)) {
					continue;
				}

				if (stopStationIndex <= sameStationIndex) { // sameStationIndex(출발역 인덱스 값)보다 stopStationIndex이 작으면  break문을 만남
					break;
				}

				isPossible = true;
				break;
			}

			if (!isPossible) {
				continue;
			}

			List<Seat> seats = train.getSeats();

			int possibleSeatCount = 0;

			for (Seat seat : seats) {
				if (!seat.isSeatStatus())
					possibleSeatCount++;
			}

			if (possibleSeatCount < dto.getNumberOfPeople()) {
				continue;
			}

			possibleTrains.add(train);
		}

		return possibleTrains;

	}

	
	public ReservationInfo postReservation(PostReservationDto postReservationDto, GetTrainListDto getTrainListDto) {
		
		Train train = null; //존재하는 열차인지 확인 결과 저장 변수 선언
		
		for (Train trainItem : trains) {
			if (postReservationDto.isEqualTrainNumber(trainItem.getTrainNumber())) {
				train = trainItem;//입력 받은 기차 번호와 같은 기차의 정보를 담는 것
				break;
			}			
		}
		
		if (train == null) { //train이 null이면 프로그램 종료
			System.out.println("존재하지 않는 열차 번호입니다.");
			return null;
		}
		
		//좌석 지정
		boolean designationState = true;
		List<Seat> seats = train.getSeats();
		List<String> inputSeatNumber = postReservationDto.getSeats();
		for (int index = 0; index < seats.size(); index++) {
			
			Seat seat = seats.get(index);
			
			for (String seatNumber : inputSeatNumber) { //입력 받은 좌석 번호를 foreach문으로 꺼내오는 것
				
				if (!seat.getSeatNumber().equals(seatNumber)) { //같지 않으면 다시 for문의 조건문을 만나야함 //여기에 AND연산자 추가해서 호차 번호를 비교하면됨
					continue;
				}//조건식에 메서드가 들어오는 것은 별로 가독성이 좋지 못함
				
				if(seat.isSeatStatus()) {//true이면 좌석이 배정되어 있는 것
					designationState = false; //입력한 좌석이 이미 배정이 되어있어 배정을 못 받게 된 상태를 표현하기위해 해당 변수에 false값을 넣는 것
					break; 
				}
				
				seat.setSeatStatus(true);
				break;
				
			}
	
			if (!designationState) {
				System.out.println("좌석 배정에 실패했습니다.");
				 return null; // designationState의 값이 false이면 입력한 좌석이 이미 배정이 되어있어 배정을 못 받게 된 상태이므로 //비정상인 종료의 값들은 전부 null로 반환하기로 했으므로
			}
			
		}

		int totalCost = 0;
		
		for (Cost cost : costs) {
			
			boolean isEqualDepartureStation = 
					getTrainListDto.isEqualDepartureStation(cost.getDepartureStation());
			boolean isEqualArrivalStation =
					getTrainListDto.isEqualArrivalStation(cost.getArrivalStation());
			
			if (!isEqualDepartureStation || !isEqualArrivalStation) continue; //둘 중에 하나라도 거짓 존재한다면 거짓이여야하므로 
			totalCost = cost.getAmount() * getTrainListDto.getNumberOfPeople();
			break;
		}
		
		
		//추가 for문 출발시간과 도착시간을 넣기 위한
		String departureTime = "";
		String arrivalTime = "";
		
		//출발역과 도착역 입력의 검증은  위의 메서드 getPossibleTrainList에서 이미 검증을 했기 때문에 아래의 반복문은 반복문을 돌려 해당하는 출발역과 도착역의
		//시간을 구하기 위해서 사용된 것
		for (StopStation stopStation : train.getStopStations()) {//train : 입력 받은 기차 번호와 같은 기차의 정보를 담는 것
			boolean isEqualDepartureStation =
					getTrainListDto.isEqualDepartureStation(stopStation.getStationName());
			boolean isEqualArrivalStation =
					getTrainListDto.isEqualArrivalStation(stopStation.getStationName());
			if (isEqualDepartureStation) departureTime = stopStation.getDepartureTime();
			if (isEqualArrivalStation) arrivalTime = stopStation.getArrivalTime();
		}
		ReservationInfo reservationInfo = new ReservationInfo(
				postReservationDto.getTrainNumber(), //이런식으로 자주 쓰이는 것은 변수명으로 빼놓으면 가독성이 좋아짐
				postReservationDto.getSeats(), //이 메서드의 위 부분을 통해서 검증 해놓은 값을 넣어 놓은 것임
				getTrainListDto.getDepartureStation(),
				departureTime,
				getTrainListDto.getArrivalStation(),
				arrivalTime,
				totalCost
		);
		reservations.add(reservationInfo); //예약을 완료한 뒤 사용자가 예약 번호를 입력할 시 해당 예약 정보를 보여주기 위해서 리스트에 저장을 하는 것
		
		return reservationInfo;
		
	}
	
	public ReservationInfo getReservation(GetReservationDto dto) {
		
		ReservationInfo reservationInfo = null;
		String reservationNumber = dto.getReservationNumber();
		for (ReservationInfo item : reservations ) { //저장된 예약 정보에서 값을 꺼내와 기차번호와 같은 것이 있는지 검사하는 for문
			
			boolean isEqualReservationNubmer = 
					reservationNumber.equals(item.getReservationNumber());
			if (!isEqualReservationNubmer) continue;
			
			reservationInfo = item;
			break;
		}
		
		return reservationInfo; //여기에 반환되는 값에는 항상 인스턴스로 생성된 후 반환된다는 보장이 없으므로 항상 예외를 잘 검증해줘야한다.
	}
	
	
	private static void initData() {
		costs.add(new Cost("부산역", "서울역", 30000));
		costs.add(new Cost("부산역", "대전역", 20000));
		costs.add(new Cost("부산역", "대구역", 10000));
		costs.add(new Cost("대구역", "서울역", 20000));
		costs.add(new Cost("대구역", "대전역", 10000));
		costs.add(new Cost("대전역", "서울역", 10000));

		costs.add(new Cost("서울역", "부산역", 30000));
		costs.add(new Cost("서울역", "대구역", 20000));
		costs.add(new Cost("서울역", "대전역", 10000));
		costs.add(new Cost("대전역", "부산역", 20000));
		costs.add(new Cost("대전역", "대구역", 10000));
		costs.add(new Cost("대구역", "부산역", 10000));

		Train train;
		List<Seat> seats = new ArrayList<Seat>();
		List<StopStation> stopStations = new ArrayList<StopStation>();

		seats.add(new Seat(1, "A1", false));
		seats.add(new Seat(1, "B1", false));
		seats.add(new Seat(1, "A2", false));
		seats.add(new Seat(1, "B2", false));
		seats.add(new Seat(2, "A1", false));
		seats.add(new Seat(2, "B1", false));
		seats.add(new Seat(2, "A2", false));
		seats.add(new Seat(2, "B2", false));
		seats.add(new Seat(3, "A1", false));
		seats.add(new Seat(3, "B1", false));
		seats.add(new Seat(3, "A2", false));
		seats.add(new Seat(3, "B2", false));

		stopStations.add(new StopStation("서울역", "", "06:00"));
		stopStations.add(new StopStation("대전역", "06:45", "07:00"));
		stopStations.add(new StopStation("대구역", "07:45", "08:00"));
		stopStations.add(new StopStation("부산역", "09:00", ""));

		train = new Train("KTX001", "서울역", "06:00", "부산역", "09:00", 180, "KTX", stopStations, seats);
		trains.add(train);

		seats = new ArrayList<Seat>();
		stopStations = new ArrayList<StopStation>();

		seats.add(new Seat(1, "A1", false));
		seats.add(new Seat(1, "B1", false));
		seats.add(new Seat(1, "A2", false));
		seats.add(new Seat(1, "B2", false));
		seats.add(new Seat(2, "A1", false));
		seats.add(new Seat(2, "B1", false));
		seats.add(new Seat(2, "A2", false));
		seats.add(new Seat(2, "B2", false));
		seats.add(new Seat(3, "A1", false));
		seats.add(new Seat(3, "B1", false));
		seats.add(new Seat(3, "A2", false));
		seats.add(new Seat(3, "B2", false));

		stopStations.add(new StopStation("부산역", "", "09:00"));
		stopStations.add(new StopStation("대구역", "09:45", "10:00"));
		stopStations.add(new StopStation("대전역", "10:45", "11:00"));
		stopStations.add(new StopStation("서울역", "12:00", ""));

		train = new Train("KTX002", "부산역", "09:00", "서울역", "12:00", 180, "KTX", stopStations, seats);
		trains.add(train);
	}

}
