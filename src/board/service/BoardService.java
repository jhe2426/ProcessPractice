package board.service;

import java.util.List;

import board.common.constant.ResponseMessage;
import board.dto.request.board.PatchBoardDto;
import board.dto.request.board.PostBoardDto;
import board.dto.response.ResponseDto;
import board.dto.response.board.DeleteBoardResponseDto;
import board.dto.response.board.GetBoardListResponseDto;
import board.dto.response.board.GetBoardResponseDto;
import board.dto.response.board.PatchBoardResponseDto;
import board.dto.response.board.PostBoardResponseDto;
import board.entity.Board;
import board.entity.User;
import board.repository.BoardRepository;
import board.repository.UserRepository;
//BoardService는 BoardRepository에 의존적이다.(의존성이 있다.)
public class BoardService {

	private BoardRepository boardRepository;
	private UserRepository userRepository;
	
	public BoardService() {
		boardRepository = new BoardRepository();
		userRepository = new UserRepository();
	}
	
	public ResponseDto<PostBoardResponseDto> postBoard(PostBoardDto dto) {
		PostBoardResponseDto data = null;
		String email = dto.getWriterEmail();
				
		User user = userRepository.findByEmail(email);
		if (user == null) return new ResponseDto<PostBoardResponseDto>(false, ResponseMessage.NOT_EXIST_USER, null);
		
		Board board = new Board(dto, user);
		boardRepository.save(board);
		
		data = new PostBoardResponseDto(board);
		
		return new ResponseDto<PostBoardResponseDto>(true, ResponseMessage.SUCCESS, data);
	}
	
	public ResponseDto<List<GetBoardListResponseDto>> getBoardList() {
		List<GetBoardListResponseDto> data = null;
		
		List<Board> boardList = boardRepository.findBy();
		
		data = GetBoardListResponseDto.copyList(boardList);
		return new ResponseDto<List<GetBoardListResponseDto>>(true, ResponseMessage.SUCCESS, data);
	}
	
	public  ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {
		 
		GetBoardResponseDto data = null;
		
		Board board = boardRepository.findByBoardNumber(boardNumber);
		if (board == null) return new ResponseDto<GetBoardResponseDto>(false,ResponseMessage.NOT_EXIST_BOARD, null);

		 board.increaseViewCount();
		boardRepository.save(board);
		
		data =  new GetBoardResponseDto(board);
		return new ResponseDto<GetBoardResponseDto>(true,ResponseMessage.SUCCESS,data);
	}
	
	public ResponseDto<PatchBoardResponseDto> patchBoard(PatchBoardDto dto) {
		
		PatchBoardResponseDto data = null;
		
		String email = dto.getEmail();
		int boardNumber = dto.getBoardNumber();
		User user = userRepository.findByEmail(email);
		if (user == null) return new ResponseDto<PatchBoardResponseDto>(false,ResponseMessage.NOT_EXIST_USER,null);
		
		Board board = boardRepository.findByBoardNumber(boardNumber);
		if (board == null) return new ResponseDto<PatchBoardResponseDto>(false,ResponseMessage.NOT_EXIST_BOARD, null);
		if (!board.getWriterEmail().equals(email)) return new ResponseDto<PatchBoardResponseDto>(false,ResponseMessage.NOT_PERMISSTION,null);
		
		board.patch(dto);
		boardRepository.save(board);
		
		data = new PatchBoardResponseDto(board);
		return new ResponseDto<PatchBoardResponseDto>(true, ResponseMessage.SUCCESS, data);
	}
	
	public ResponseDto<List<DeleteBoardResponseDto>> deleteBoard(int boardNubmer, String email) {
		
		List<DeleteBoardResponseDto> data = null;
		
		User user = userRepository.findByEmail(email);
		if (user == null) return new ResponseDto<List<DeleteBoardResponseDto>>(false, ResponseMessage.NOT_EXIST_USER, null);
		
		
		Board board = boardRepository.findByBoardNumber(boardNubmer);
		if (board == null) return new ResponseDto<List<DeleteBoardResponseDto>>(false, ResponseMessage.NOT_EXIST_BOARD, null);
		if (!board.getWriterEmail().equals(email)) return new ResponseDto<List<DeleteBoardResponseDto>>(false, ResponseMessage.NOT_PERMISSTION, null);
		
		 boardRepository.deleteByBoardNumber(boardNubmer);

		 List<Board> boardList = boardRepository.findBy();
		data = DeleteBoardResponseDto.copyList(boardList); 
		return new ResponseDto<List<DeleteBoardResponseDto>>(true, ResponseMessage.SUCCESS, data);
		
	}
}
