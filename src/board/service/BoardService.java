package board.service;

import board.repository.BoardRepository;
//BoardService는 BoardRepository에 의존적이다.(의존성이 있다.)
public class BoardService {

	private BoardRepository boardRepository;
	
	public BoardService() {
		boardRepository = new BoardRepository();
	}
	
}
