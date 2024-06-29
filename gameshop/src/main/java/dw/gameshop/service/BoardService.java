package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Board;
import dw.gameshop.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().filter((b)->b.getIsActive()).collect(Collectors.toList());
    }

    public Board saveBoard(Board board) {
        return boardRepository.findById(board.getId())
                .map((existingBoard)->{
                    existingBoard.setModifiedDate(LocalDateTime.now());
                    return boardRepository.save(existingBoard);
                })
                .orElseGet(()-> {
                    board.setCreatedDate(LocalDateTime.now());
                    board.setModifiedDate(LocalDateTime.now());
                    return boardRepository.save(board);
                });
    }

    public String deleteBoard(long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setIsActive(false);
                    boardRepository.save(board);
                    return "successfully deleted";
                })
                .orElseThrow(() -> new ResourceNotFoundException("게시판 글이 없습니다.", "id", id));
    }
}
