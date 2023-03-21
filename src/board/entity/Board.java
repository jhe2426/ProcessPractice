package board.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.dto.request.board.PatchBoardDto;
import board.dto.request.board.PostBoardDto;
import board.repository.BoardRepository;

//		게시물
//		게시물의 필요한 데이터들 : 이미지 / 작성자 닉네임 / 작성자 프로필 사진 / 작성일 / 제목 / 내용 / 댓글 수 / 좋아요 수 
//		/ 조회수 / 좋아요(좋아요한 프로필 사진, 좋아요한 사용자 닉네임) 리스트
//		/ 댓글(댓글 작성자 프로필 사진, 댓글 작성자 닉네임, 댓글 작성 지난 시간, 댓글 내용) 리스트
public class Board {
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
	
	public Board() {}

	public Board(int boardNumber, String boardImageUrl, String writerEmail, String writerNickname,
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

	public Board(PostBoardDto dto, User user) {
		Date now = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.boardNumber = ++BoardRepository.index;
		this.boardImageUrl = dto.getBoardImageUrl();
		this.writerEmail = user.getEmail();
		this.writerNickname = user.getNickname();
		this.writerProfileImageUrl = user.getProfileImageUrl();
		this.writeDate = simpleDateFormat.format(now);
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.viewCount = 0;
		this.likeList = new ArrayList<Like>();
		this.commentList = new ArrayList<Comment>();
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
		return "Board [boardNumber=" + boardNumber + ", boardImageUrl=" + boardImageUrl + ", writerEmail=" + writerEmail
				+ ", writerNickname=" + writerNickname + ", writerProfileImageUrl=" + writerProfileImageUrl
				+ ", writeDate=" + writeDate + ", title=" + title + ", content=" + content + ", viewCount=" + viewCount
				+ ", likeList=" + likeList + ", commentList=" + commentList + "]";
	}
	
	public void increaseViewCount() {
		this.viewCount++;
	}
	
	public void patch(PatchBoardDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.boardImageUrl = dto.getBoardImageUrl();
	}
}
