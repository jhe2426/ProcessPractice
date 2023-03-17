package board.controller;

import board.service.BoardService;
//BoardController는 BoardService에 의존적이다.(의존성이 있다.)
public class BoardController {

	private BoardService boardService;
	
	public BoardController() {
		boardService = new BoardService();
	}
}
