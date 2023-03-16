package board.entity;
//회원

//	 회원의 필요한 데이터들 : 이메일 주소 (문자열) / 비밀번호 (문자열) / 비밀번호 확인 (문자열){얘는 멤버 변수로 선언 안 함) /  닉네임  (문자열)  
//	 핸드폰번호 (문자열) / 주소 (문자열) / 상세 주소 (문자열) 
//	 프로필 사진 (문자열)

public class User {
	//쭉 관리가 필요한 데이터인지 연산용인지를 잘 생각해서 멤버 변수로 선언을 해야함
	private String email;
	private String password;
	//private String passwordCheck; //입력 받는 순간에 검증을 하는 용도이므로 쭉 계속 관리할 필요는 없으므로 멤버 변수로 선언하지 않을 것이다.
	private String nickName;
	private String phoneNumber;
	private String address;
	private String addressDetail;
	private String profileImageUrl;

	private User() {}

	public User(String email, String password, String nickName, String phoneNumber, String address,
			String addressDetail, String profileImageUrl) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.addressDetail = addressDetail;
		this.profileImageUrl = profileImageUrl;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	//지금은 데이터베이스가 없어서 인스턴스 값 수정이 기능상 필요하므로  setter를 선언해둔 것
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", nickName=" + nickName + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", addressDetail=" + addressDetail + ", profileImageUrl="
				+ profileImageUrl + "]";
	}
	
	
}
