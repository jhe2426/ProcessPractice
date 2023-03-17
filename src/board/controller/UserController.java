package board.controller;

import board.common.constant.HttpStatus;
import board.dto.request.user.SignInDto;
import board.dto.request.user.SignUpDto;
import board.dto.response.ResponseDto;
import board.dto.response.user.SignInResponseDto;
import board.service.UserService;

//입력 받은 것을 여기서 검증할 것
public class UserController {

	private UserService userService;
	
	public UserController() {
		userService = new UserService();
	}
	
	public void signUp(SignUpDto dto) {
				
		if (dto.validation()) {
			System.out.println(HttpStatus.BAD_REQUEST);//400 :사용자로부터 입력 받야할 것을 받지 못했을 때 서버에서 뱉는 에러 코드
			return; 
			//비밀번호가 같은지 아닌지를 여기서 검증하지 않을 것(왜냐하면 이것은 비지니스 로직에 해당하기 때문이다.)
		}
		
		ResponseDto<Boolean> response = userService.signUp(dto);
		System.out.println(response.toString());
		
	}
	
	public void signIn(SignInDto dto) {
		
		if (dto.validate()) {
			System.out.println(HttpStatus.BAD_REQUEST);
			return;
		}
		
		ResponseDto<SignInResponseDto> response = userService.signIn(dto);
		System.out.println(response.toString());
		
	}
	
}
