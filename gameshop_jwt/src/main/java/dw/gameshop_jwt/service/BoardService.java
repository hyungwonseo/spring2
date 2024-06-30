package dw.gameshop_jwt.service;

import dw.gameshop_jwt.dto.BoardDto;
import dw.gameshop_jwt.exception.ResourceNotFoundException;
import dw.gameshop_jwt.model.Board;
import dw.gameshop_jwt.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<BoardDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll()
                .stream().filter((b)->b.getIsActive()).collect(Collectors.toList());
        return boardList.stream().map((b)->BoardDto.toBoardDto(b)).collect(Collectors.toList());
    }

    public BoardDto saveBoard(Board board) {
        return boardRepository.findById(board.getId())
                .map((existingBoard)->{
                    existingBoard.setModifiedDate(LocalDateTime.now());
                    return BoardDto.toBoardDto(boardRepository.save(existingBoard));
                })
                .orElseGet(()-> {
                    board.setCreatedDate(LocalDateTime.now());
                    board.setModifiedDate(LocalDateTime.now());
                    return BoardDto.toBoardDto(boardRepository.save(board));
                });
    }

    public String deleteBoard(long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setIsActive(false);
                    boardRepository.save(board);
                    return "successfully deleted";
                })
                .orElseThrow(() -> new ResourceNotFoundException("게시판 글이 없습니다. ID : " + id));
    }
}
