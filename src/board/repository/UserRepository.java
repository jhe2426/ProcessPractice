package board.repository;

import java.util.ArrayList;
import java.util.List;

import board.entity.User;
//DAO : 데이터 접근 객체(Data Access Object) == Repository
public class UserRepository {

	private static List<User> userTable = new ArrayList<User>();
	
	//유저의 이메일을 가지고 유저의 정보를 찾는 메서드
	//실제 기능에서는 비밀번호가 암호화 되어 있기 때문에 여기에서 한 번에 이메일과 비밀번호가 일치한 유저의 정보를 가져올 수 가 없다.
	public User findByEmail(String email) { 
		User result = null;
		for (User user : userTable) {
			if (user.getEmail().equals(email)) {
				result = user;
				break;
			}
		}
		
		return result;
	}
	
	
	//이메일 중복확인
	public boolean existsByEmail(String email) {
		boolean result = false;
		for (User user : userTable) {
			if (user.getEmail().equals(email)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	//회원가입한 유저의 정보 저장
	public User save(User user) {
		userTable.add(user);
		return user;
	}
	
}
