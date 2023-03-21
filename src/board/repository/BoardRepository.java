package board.repository;

import java.util.ArrayList;
import java.util.List;

import board.entity.Board;

public class BoardRepository {
	
	public static int index = 0;
	
	private static List<Board> boardTable = new ArrayList<Board>();
	
	public Board save(Board board) {
		boolean isExist = false;
		for (int index = 0; index < boardTable.size(); index++) {
			Board boardItem = boardTable.get(index);
			if (boardItem.getBoardNumber() == board.getBoardNumber()) {
				boardTable.set(index, board); //같은 번호의 게시물을 수정하고 다시 리스트에 넣는 거
				isExist = true;
				break;
			}
		}
		
		if (!isExist) boardTable.add(board); //같은 게시물 번호가 존재하지 않는다면 그냥 리스트에 추가해라
		return board;
	}

	public Board findByBoardNumber(int boardNumber) {
		Board result = null;
		for (Board board : boardTable) {
			if (board.getBoardNumber() == boardNumber) {
				result = board;
				break;
			}
		}
		return result;
	}
	
	public  List<Board> findBy() {
		return boardTable; //boardTable는 static이므로 인스턴스가 아니고 클래스변수이므로 밑에 findBy에 this로 접근할 필요가 없는 것
	}
	
	public void deleteByBoardNumber(int boardNumber) {
		for (int index = 0; index < boardTable.size(); index++) {
			Board board = boardTable.get(index);
			if (board.getBoardNumber() == boardNumber) {
				boardTable.remove(board); //remove메서드에는 인스턴스를 넣어도 되고 인덱스 번호를 넣어도 됨
				break;
			}
		}
	}
}
