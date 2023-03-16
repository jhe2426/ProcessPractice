package board.entity;
//		좋아요 

//		좋아요한 사용자 이메일(좋아요를 취소할 때 필요한 정보이므로) (문자열) / 
//		좋아요한 프로필 사진 (문자열) / 
//		좋아요한 사용자 닉네임 (문자열)
public class Like {
	private String userEmail;
	private String userProfileImageUrl;
	private String userNickname;
	
	public Like() {}

	public Like(String userEmail, String userProfileImageUrl, String userNickname) {
		this.userEmail = userEmail;
		this.userProfileImageUrl = userProfileImageUrl;
		this.userNickname = userNickname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}

	public void setUserNickName(String userNickname) {
		this.userNickname = userNickname;
	}

	@Override
	public String toString() {
		return "Like [userEmail=" + userEmail + ", userProfileImageUrl=" + userProfileImageUrl + ", userNickname="
				+ userNickname + "]";
	}
	
}
