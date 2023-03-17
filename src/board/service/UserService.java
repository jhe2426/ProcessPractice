package board.service;

import board.common.constant.ResponseMessage;
import board.dto.request.user.SignInDto;
import board.dto.request.user.SignUpDto;
import board.dto.response.ResponseDto;
import board.dto.response.user.SignInResponseDto;
import board.entity.User;
import board.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public UserService() {
		userRepository = new UserRepository();
	}
	
	public ResponseDto<Boolean> signUp(SignUpDto dto) {
		
		String email = dto.getEmail();
		String password = dto.getPassword();
		String passwordCheck = dto.getPasswordCheck();
		
		//이메일 중복확인
		boolean hasEmail = userRepository.existsByEmail(email); 
		if (hasEmail) 
			return new ResponseDto<Boolean>(false, ResponseMessage.EXIST_EMAIL, false);
		
		//비밀번호 재확인
		if (!password.equals(passwordCheck)) {
			return new ResponseDto<Boolean>(false, ResponseMessage.PASSWORD_NOT_MATCH, false);
		}
		
		//회원정보 저장
		User user = new User(dto);
		userRepository.save(user);
		
		return new ResponseDto<Boolean>(true, ResponseMessage.SUCCESS, true);
	}
	
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
		
		SignInResponseDto data = null;
		
		String email = dto.getEmail();
		String password = dto.getPassword();
		
		//아래에 회원의 정보를 가져올 때 이메일과 비밀번호를 비교하지 않는 이유는 실제 기능에서는 비밀번호가 암호화 되어 있기 때문에 
		// 한 번에 이메일과 비밀번호가 일치한 유저의 정보를 가져올 수 가 없다.
		User user = userRepository.findByEmail(email);
		if (user == null) {//user == null :입력한 이메일 주소에 해당하는 유저가 없다는 것
			return new ResponseDto<SignInResponseDto>(false, ResponseMessage.FAIL_SIGN_IN, null);
		}
		
		boolean isEqualPassword = user.getPassword().equals(password);
		if (!isEqualPassword) {
			return new ResponseDto<SignInResponseDto>(false, ResponseMessage.FAIL_SIGN_IN, null);
		}
 		
		data = new SignInResponseDto(user);
		
		
		return new ResponseDto<SignInResponseDto>(true, ResponseMessage.SUCCESS, data);
		
	}
	
}
