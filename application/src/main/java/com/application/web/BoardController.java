package com.application.web;

import com.application.mapper.scrumboard.BoardMapper;
import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardList;
import com.scrumboard.domain.Card;
import com.scrumboard.repository.BoardListRepository;
import com.scrumboard.service.BoardListService;
import com.scrumboard.service.BoardService;
import com.scrumboard.service.CardService;
import com.utility.dto.scrumboard.BoardDTO;
import com.utility.dto.scrumboard.BoardListDTO;
import com.utility.dto.scrumboard.CardDTO;
import com.utility.exception.NotFoundException;
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
    private CardService cardService;
    private BoardListRepository boardListRepository;

    @Autowired
    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           BoardListService boardListService,
                           CardService cardService,
                           BoardListRepository boardListRepository) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardListService = boardListService;
        this.cardService = cardService;
        this.boardListRepository = boardListRepository;
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
    public List<BoardListDTO> newBoardList(@PathVariable Long boardId,
                                           @PathVariable String name) {
        boardListService.newBoardList(boardId, name);

        Board board = boardService.getBoardById(boardId);

        return boardMapper.mapToBoardDTO(board).getLists();
    }

    @GetMapping(value = "/delete", produces = APPLICATION_JSON_VALUE)
    public void deleteAllBoards() {
        boardService.deleteAllBoards();
    }

    @PostMapping(value = "/{boardId}/list/{listId}/cardTitle={cardTitle}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    BoardDTO createNewCard(@PathVariable Long boardId,
                           @PathVariable Long listId,
                           @PathVariable String cardTitle) {
        Card card = cardService.createNewCard(boardId, cardTitle, listId);

//        cardService.addCardIdToBoardList(card.getId(), listId);

        return boardMapper.mapToBoardDTO(boardService.getBoardById(boardId));
    }

//    @PutMapping(value = "/test", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    CardDTO updateCard(@RequestBody CardDTO cardDTO) {
//
//    }
}
