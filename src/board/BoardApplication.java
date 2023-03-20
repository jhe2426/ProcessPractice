package board;

import java.util.Scanner;

import board.common.constant.HttpStatus;
import board.controller.BoardController;
import board.controller.UserController;
import board.dto.request.board.PostBoardDto;
import board.dto.request.user.SignInDto;
import board.dto.request.user.SignUpDto;
//입력은 여기서 받을 것
public class BoardApplication {
	

	private static UserController userController = new UserController();
	private static BoardController boardController = new BoardController();
	
	private static final String SIGN_UP  = "POST /sign-up";
	private static final String SIGN_IN  = "POST /sign-in";
	
	private static final String POST_BOARD = "POST /board";
	
	private static final String GET_BOARD = "GET /board";
	private static final String GET_BOARD_LIST = "GET /board/list";

	private static final String PATCH_BOARD = "PATCH /board";//PATCH : 수정(덮어씌우다.)
	private static final String DELETE_BOARD = "DELETE /board";
	
	public static void main(String[] args) {
		
		while(true) {
			
			Scanner scanner  = new Scanner(System.in);
			
			System.out.print("URL End point : ");
			String endPoint = scanner.nextLine();
			
			switch(endPoint) {
			
			case SIGN_UP :
				SignUpDto signUpDto = new SignUpDto();
				System.out.print("이메일 주소 : ");
				signUpDto.setEmail(scanner.nextLine());
				System.out.print("비밀번호 : ");
				signUpDto.setPassword(scanner.nextLine());
				System.out.print("비밀번호 확인 : ");
				signUpDto.setPasswordCheck(scanner.nextLine());
				System.out.print("닉네임 : ");
				signUpDto.setNickname(scanner.nextLine());
				System.out.print("휴대전화 번호 : ");
				signUpDto.setPhoneNumber(scanner.nextLine());
				System.out.print("주소 : ");
				signUpDto.setAddress(scanner.nextLine());
				System.out.print("상세 주소 : ");
				signUpDto.setAddressDetail(scanner.nextLine());
				
				//System.out.println(signUpDto.toString());
				
				userController.signUp(signUpDto);
				break;
			
			case SIGN_IN :
				SignInDto signInDto = new SignInDto();
				System.out.print("이메일 주소 : ");
				signInDto.setEmail(scanner.nextLine());
				System.out.print("비밀번호 : ");
				signInDto.setPassword(scanner.nextLine());
				
				//System.out.println(signInDto.toString());
				userController.signIn(signInDto);
				break;
				
			case POST_BOARD :
				PostBoardDto postBoardDto = new PostBoardDto();
				System.out.print("제목 : ");
				postBoardDto.setTitle(scanner.nextLine());
				System.out.print("내용 : ");
				postBoardDto.setContent(scanner.nextLine());
				System.out.print("이미지 파일 : ");
				postBoardDto.setBoardImageUrl(scanner.nextLine());
				System.out.print("작성자 이메일 : ");
				postBoardDto.setWriterEmail(scanner.nextLine());
				
				boardController.postBoard(postBoardDto);
				break;
				
			case GET_BOARD_LIST :
				boardController.getBoardList();
				break;
				
			case GET_BOARD ://게시물 상세보기
				//GET, DETLE 통신 방식은 DTO클래스를 만들지 않을 것이다.
				//외부에서 내부에서 데이터가 넘어오는 거
				//외부에서 무조건 정수로 입력하지 않을 수 있음
				//외부에서 받아오는 모든 것은 예외가 발생할 수 있음
				//그래서 예외 처리를 꼭 해줘야함
				int boardNumber = 0;
				try {//try-catch문에 선언되어있는 변수들은 지역변수임
					System.out.print("게시물 번호 :");
					boardNumber = scanner.nextInt();
				} catch (Exception exception) {
					exception.printStackTrace();
					continue;
				}

				boardController.getBoard(boardNumber);
				
				break;	
			default:
				System.out.println(HttpStatus.NOT_FOUND);
			}
			
		}
		
	
		
		
		
	}

}
