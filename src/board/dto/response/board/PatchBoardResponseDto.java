package board.dto.response.board;

import java.util.List;

import board.entity.Board;
import board.entity.Comment;
import board.entity.Like;

public class PatchBoardResponseDto {
//ResponseDto의 반환을
	
//1.성능 실패 성공여부만 반환해준다고 했을 때
//장점 메서드의 기능이 매우 명확해진다. 
//조금 더 유연하게 할 수 있다.(수정이 필요할 시 ResponseDto를 수정할 필요가 없음)
//하지만 프론트 작업이 힘들어진다.(코드가 복잡해지기 때문)

	
//2.연관된 것을 반환을 해줄 때 
//단점은 의존되어있어서  ResponseDto클래스 코드 수정이 굉장히 힘들어진다.
//--오픈API를 사용할 때는 이런식으로 반환한다고 함 
	private int boardNumber;
	private String title;
	private String content;
	private String boardImageUrl;
	private String writerEmail;
	private String writerProfileImageUrl;
	private String writerNickname;
	private String writerDate;
	private int likeCount;
	private List<Like> likeList;
	private int commentCount;
	private List<Comment> commentList;
	
	public PatchBoardResponseDto() {}


	public PatchBoardResponseDto(int boardNumber, String title, String content, String boardImageUrl,
			String writerEmail, String writerProfileImageUrl, String writerNickname, String writerDate, int likeCount,
			List<Like> likeList, int commentCount, List<Comment> commentList) {
		this.boardNumber = boardNumber;
		this.title = title;
		this.content = content;
		this.boardImageUrl = boardImageUrl;
		this.writerEmail = writerEmail;
		this.writerProfileImageUrl = writerProfileImageUrl;
		this.writerNickname = writerNickname;
		this.writerDate = writerDate;
		this.likeCount = likeCount;
		this.likeList = likeList;
		this.commentCount = commentCount;
		this.commentList = commentList;
	}



	public PatchBoardResponseDto(Board board) {
		this.boardNumber = board.getBoardNumber();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.boardImageUrl = board.getBoardImageUrl();
		this.writerEmail = board.getWriterEmail();
		this.writerProfileImageUrl = board.getWriterProfileImageUrl();
		this.writerNickname = board.getWriterNickname();
		this.writerDate = board.getWriteDate();
		this.likeCount = board.getLikeList().size();
		this.likeList = board.getLikeList();
		this.commentCount = board.getCommentList().size();
		this.commentList = board.getCommentList();
	}


	public int getBoardNumber() {
		return boardNumber;
	}


	public String getTitle() {
		return title;
	}


	public String getContent() {
		return content;
	}


	public String getBoardImageUrl() {
		return boardImageUrl;
	}


	public String getWriterEmail() {
		return writerEmail;
	}


	public String getWriterProfileImageUrl() {
		return writerProfileImageUrl;
	}


	public String getWriterNickname() {
		return writerNickname;
	}


	public String getWriterDate() {
		return writerDate;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public List<Like> getLikeList() {
		return likeList;
	}


	public int getCommentCount() {
		return commentCount;
	}


	public List<Comment> getCommentList() {
		return commentList;
	}


	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setBoardImageUrl(String boardImageUrl) {
		this.boardImageUrl = boardImageUrl;
	}


	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}


	public void setWriterProfileImageUrl(String writerProfileImageUrl) {
		this.writerProfileImageUrl = writerProfileImageUrl;
	}


	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}


	public void setWriterDate(String writerDate) {
		this.writerDate = writerDate;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}


	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}


	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}


	@Override
	public String toString() {
		return "PatchBoardResponseDto [boardNumber=" + boardNumber + ", title=" + title + ", content=" + content
				+ ", boardImageUrl=" + boardImageUrl + ", writerEmail=" + writerEmail + ", writerProfileImageUrl="
				+ writerProfileImageUrl + ", writerNickname=" + writerNickname + ", writerDate=" + writerDate
				+ ", likeCount=" + likeCount + ", likeList=" + likeList + ", commentCount=" + commentCount
				+ ", commentList=" + commentList + "]";
	}
	
	
	
}
	
	