package com.application.web;

import com.application.mapper.scrumboard.BoardMapper;
import com.scrumboard.domain.Board;
import com.scrumboard.service.BoardListService;
import com.scrumboard.service.BoardService;
import com.utility.dto.scrumboard.BoardDTO;
import com.utility.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private BoardService boardService;
    private BoardMapper boardMapper;
    private BoardListService boardListService;

    @Autowired
    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           BoardListService boardListService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardListService = boardListService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public BoardDTO createNewEmptyBoard() {
        Board board = boardService.createNewEmptyBoard();

        return boardMapper.mapToBoardDTO(board);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<BoardDTO> getBoards() {
        return boardMapper.mapToBoardDTOList(boardService.getBoards());
    }

    @GetMapping(value = "/{boardId}", produces = APPLICATION_JSON_VALUE)
    public BoardDTO getBoardById(@PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId);

        return boardMapper.mapToBoardDTO(board);
    }

    @PostMapping(value = "/{boardId}/list/{name}", produces = APPLICATION_JSON_VALUE)
    public ApiResponse newBoardList(@PathVariable Long boardId,
                                    @PathVariable String name) {
        boardListService.newBoardList(boardId, name);

        return new ApiResponse(true, "List added. ");
    }
}
