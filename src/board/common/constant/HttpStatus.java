package board.common.constant;

//모든 요청의 반환을 한국어보다 영어를 사용하는 것이 좋다 한글은 깨질 수도 있기 때문
public interface HttpStatus {
	public static final String BAD_REQUEST = "400 Bad Request";
	public static final String UNAUTHORIZED = "401 Unauthorized"; //401 : 인증 실패 코드
	public static final String NOT_FOUND = "404 Not Found";
}
