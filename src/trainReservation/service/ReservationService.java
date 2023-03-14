package trainReservation.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import trainReservation.dto.GetTrainListDto;
import trainReservation.entity.Cost;
import trainReservation.entity.Seat;
import trainReservation.entity.StopStation;
import trainReservation.entity.Train;

//Service class (계층)
//실제 비즈니스 로직을 담당

public class ReservationService {
	
		private static List<Train> trains = new ArrayList<Train>();
		private static List<Cost> costs = new ArrayList<Cost>();
		
		private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		public ReservationService() {
				initData();
		}
		
		
		public List<Train> getPossibleTrainList(GetTrainListDto dto, LocalTime departureTime) {
			
			//최종으로 리턴 받을 결과물임 : possibleTrains
			List<Train> possibleTrains = new ArrayList<Train>(); // while문 위쪽에 선언하는 것이 좋긴함
				
			//조회만 할 것이라서 foreach문 사용
			for (Train train : trains) { //해당 index값이 필요하지도, 값의 변경도 있지 않으므로 foreach문으로 사용한 것
					List<StopStation> stopStations =  train.getStopStations(); //??이렇게 해도 왜 오류가 안 나는지 생각해보기
	//				List<StopStation> stopStations = new ArrayList<StopStation>();
	//				stopStations = train.getStopStations();
					int sameStationIndex = -1;
					
					for (int stopStationIndex = 0; stopStationIndex < stopStations.size(); stopStationIndex++) { //인덱스가 필요하므로 foreach문을 사용하지 않은 것
						
							String stopStationName = stopStations.get(stopStationIndex).getStationName();
						
							//출발역의 이름이 정차역이름과 같은지
							if (!dto.isEqualDepartureStation(stopStationName)) {
								continue;
							}
							
							LocalTime stationDepartureTime = LocalTime.parse(stopStations.get(stopStationIndex).getDepartureTime(), timeFormatter);
							
							//입력한 시간 이후의 열차가 존재하는지에 대한 구문
							//isBefore() : 지나간 날짜인지 비교하여 boolean 값 반환 stationDepartureTime이 departureTime보다 이전인지 확인하는 것
							if (stationDepartureTime.isBefore(departureTime)) { //시간이 지나면 break을 만나게 됨
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
							
							if (stopStationIndex <= sameStationIndex) { //sameStationIndex(출발역 인덱스 값)보다 stopStationIndex이 작으면 break문을 만남
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
					
					for (Seat seat: seats) {
							if (!seat.isSeatStatus()) possibleSeatCount++;
					}
					
					if (possibleSeatCount < dto.getNumberOfPeople()) {
							continue;
					}
					
					possibleTrains.add(train);
			}
			
			return possibleTrains;
			
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
			
			train = new Train("KTX001", "서울역", "06:00", "부산역", "09:00", 180, "KTX", stopStations , seats);
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
			
			train = new Train("KTX002", "부산역", "09:00", "서울역", "12:00", 180, "KTX", stopStations , seats);
			trains.add(train);
		}
	
}
