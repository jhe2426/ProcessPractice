package board.dto.response.board;

import java.util.List;

import board.entity.Board;
import board.entity.Comment;
import board.entity.Like;
//Board Entity와 똑같은 멤버 변수를 가지고 있는데 이 클래스를 사용하는 이유는
//지금은 반환하려는 멤버 개수가 다 똑같지만
//언제 어떻게 반환하려는 멤버가 달라질 수 있는지 모르기 때문에
//Entity를 반환해서 사용하려면 유지보수할 때 매우 불편해지기 때문에
//이런식으로 Dto를 만들어서 사용하는 습관을 들여야한다.
public class PostBoardResponseDto {

	private int boardNumber;
	private String boardImageUrl;
	private String writerEmail;
	private String writerNickname;
	private String writerProfileImageUrl;
	private String writeDate;
	private String title;
	private String content;
	private int viewCount;
	private List<Like> likeList;
	private List<Comment> commentList;
	
	public PostBoardResponseDto() {}

	public PostBoardResponseDto(int boardNumber, String boardImageUrl, String writerEmail, String writerNickname,
			String writerProfileImageUrl, String writeDate, String title, String content, int viewCount,
			List<Like> likeList, List<Comment> commentList) {
		this.boardNumber = boardNumber;
		this.boardImageUrl = boardImageUrl;
		this.writerEmail = writerEmail;
		this.writerNickname = writerNickname;
		this.writerProfileImageUrl = writerProfileImageUrl;
		this.writeDate = writeDate;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.likeList = likeList;
		this.commentList = commentList;
	}

	public PostBoardResponseDto(Board board) {
		this.boardNumber = board.getBoardNumber();
		this.boardImageUrl = board.getBoardImageUrl();
		this.writerEmail = board.getWriterEmail();
		this.writerNickname = board.getWriterNickname();
		this.writerProfileImageUrl = board.getWriterProfileImageUrl();
		this.writeDate = board.getWriteDate();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.viewCount = board.getViewCount();
		this.likeList = board.getLikeList();
		this.commentList = board.getCommentList();
	}
	
	public int getBoardNumber() {
		return boardNumber;
	}

	public String getBoardImageUrl() {
		return boardImageUrl;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public String getWriterNickname() {
		return writerNickname;
	}

	public String getWriterProfileImageUrl() {
		return writerProfileImageUrl;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public List<Like> getLikeList() {
		return likeList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public void setBoardImageUrl(String boardImageUrl) {
		this.boardImageUrl = boardImageUrl;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}

	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}

	public void setWriterProfileImageUrl(String writerProfileImageUrl) {
		this.writerProfileImageUrl = writerProfileImageUrl;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "PostBoardResponseDto [boardNumber=" + boardNumber + ", boardImageUrl=" + boardImageUrl
				+ ", writerEmail=" + writerEmail + ", writerNickname=" + writerNickname + ", writerProfileImageUrl="
				+ writerProfileImageUrl + ", writeDate=" + writeDate + ", title=" + title + ", content=" + content
				+ ", viewCount=" + viewCount + ", likeList=" + likeList + ", commentList=" + commentList + "]";
	}
		
}
