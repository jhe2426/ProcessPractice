package board.entity;
//		댓글 (댓글은 수정 삭제가 없기 때문에 작성한 사람을 구별할 필요가 없으므로 사용자 이메일이 필요하지 않는 것)
//		댓글 작성자 프로필 사진 (문자열) / 
//		댓글 작성자 닉네임 (문자열) / 
//		댓글 작성 날짜 및 지난 시간 (날짜) / 
//		(위의 날짜와 아래의 날짜는 주된 용도가 다름 위의 날짜는 보여주는 용도가 가장 많이 사용되므로 문자열  여기의 날짜는 현재 시간과의 연산만 잠깐 필요한 용도이므로 날짜형으로 선언을 한다고 함)
//		댓글 내용 (문자열)

import java.util.Date;

public class Comment {
	private String writerProfileImageUrl;
	private String writerNickname;
	private Date writeDateTime;
	private String content;
	
	public Comment() {}

	public Comment(String writerProfileImageUrl, String writerNickname, Date writeDateTime, String content) {
		this.writerProfileImageUrl = writerProfileImageUrl;
		this.writerNickname = writerNickname;
		this.writeDateTime = writeDateTime;
		this.content = content;
	}

	public String getWriterProfileImageUrl() {
		return writerProfileImageUrl;
	}

	public String getWriterNickname() {
		return writerNickname;
	}

	public Date getWriteDateTime() {
		return writeDateTime;
	}

	public String getContent() {
		return content;
	}

	public void setWriterProfileImageUrl(String writerProfileImageUrl) {
		this.writerProfileImageUrl = writerProfileImageUrl;
	}

	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}

	public void setWriteDateTime(Date writeDateTime) {
		this.writeDateTime = writeDateTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [writerProfileImageUrl=" + writerProfileImageUrl + ", writerNickname=" + writerNickname
				+ ", writeDateTime=" + writeDateTime + ", content=" + content + "]";
	}
	
}
