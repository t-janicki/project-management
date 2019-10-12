package com.application.web;

import com.application.mapper.scrumboard.*;
import com.auth.security.CurrentUser;
import com.auth.security.UserPrincipal;
import com.scrumboard.domain.*;
import com.scrumboard.service.*;
import com.utility.dto.scrumboard.*;
import com.utility.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private BoardService boardService;
    private BoardMapper boardMapper;
    private BoardListService boardListService;
    private CardService cardService;
    private CardMapper cardMapper;
    private CheckListService checkListService;
    private CheckItemService checkItemService;
    private BoardListMapper boardListMapper;
    private LabelService labelService;
    private LabelMapper labelMapper;
    private ActivityService activityService;
    private ActivityMapper activityMapper;
    private BoardSettingsMapper boardSettingsMapper;

    @Autowired
    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           BoardListService boardListService,
                           CardService cardService,
                           CardMapper cardMapper,
                           CheckListService checkListService,
                           CheckItemService checkItemService,
                           BoardListMapper boardListMapper,
                           LabelService labelService,
                           LabelMapper labelMapper,
                           ActivityService activityService,
                           ActivityMapper activityMapper,
                           BoardSettingsMapper boardSettingsMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardListService = boardListService;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.checkListService = checkListService;
        this.checkItemService = checkItemService;
        this.boardListMapper = boardListMapper;
        this.labelService = labelService;
        this.labelMapper = labelMapper;
        this.activityService = activityService;
        this.activityMapper = activityMapper;
        this.boardSettingsMapper = boardSettingsMapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public BoardDTO createNewEmptyBoard(@CurrentUser UserPrincipal userPrincipal) {
        Board board = boardService.createNewEmptyBoard(userPrincipal.getId());

        return boardMapper.mapToBoardDTO(board);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<BoardDTO> getBoards(@CurrentUser UserPrincipal userPrincipal) {
        List<Board> boards = boardService.getBoards(userPrincipal.getId());

        return boardMapper.mapToBoardDTOList(boards);
    }

    @GetMapping(value = "/{boardId}", produces = APPLICATION_JSON_VALUE)
    public BoardDTO getBoardById(@CurrentUser UserPrincipal userPrincipal,
                                 @PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId, userPrincipal.getId());

        return boardMapper.mapToBoardDTO(board);
    }

    @PutMapping(value = "/{boardId}/name={name}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse renameBoard(@CurrentUser UserPrincipal userPrincipal,
                                   @PathVariable Long boardId,
                                   @PathVariable String name) {

        boardService.renameBoard(boardId, userPrincipal.getId(), name);

        return new ApiResponse(true, "Board renamed. ");
    }

    @PutMapping(value = "/{boardId}/settings",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardSettings updateBoardSettings(@CurrentUser UserPrincipal userPrincipal,
                                             @RequestBody BoardDTO boardDTO,
                                             @PathVariable Long boardId) {

        BoardSettings boardSettings = boardSettingsMapper.mapToBoardSettings(boardDTO.getSettings());

        return boardService.updateSettings(boardId, userPrincipal.getId(), boardSettings);
    }

    @DeleteMapping(value = "/{boardId}")
    public ApiResponse deleteBoard(@CurrentUser UserPrincipal userPrincipal,
                                   @PathVariable Long boardId) {
        boardService.deleteBoardById(boardId, userPrincipal.getId());

        return new ApiResponse(true, "Board deleted. ");
    }

    @PostMapping(value = "/{boardId}/list/{name}", produces = APPLICATION_JSON_VALUE)
    public List<BoardListDTO> newBoardList(@CurrentUser UserPrincipal userPrincipal,
                                           @PathVariable Long boardId,
                                           @PathVariable String name) {
        boardListService.newBoardList(boardId, userPrincipal.getId(), name);

        Board board = boardService.getBoardById(boardId, userPrincipal.getId());

        return boardMapper.mapToBoardDTO(board).getLists();
    }

    @GetMapping(value = "/delete", produces = APPLICATION_JSON_VALUE)
    public void deleteAllBoards() {
        boardService.deleteAllBoards();
    }

    @PostMapping(value = "/{boardId}/list/{listId}/cardTitle={cardTitle}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    BoardDTO createNewCard(@CurrentUser UserPrincipal userPrincipal,
                           @PathVariable Long boardId,
                           @PathVariable Long listId,
                           @PathVariable String cardTitle) {
        cardService.createNewCard(boardId, userPrincipal.getId(), cardTitle, listId);

        return boardMapper.mapToBoardDTO(boardService.getBoardById(boardId, userPrincipal.getId()));
    }

    @DeleteMapping(value = "/{boardId}/card/{cardId}")
    public List<BoardListDTO> deleteCard(@CurrentUser UserPrincipal userPrincipal,
                                         @PathVariable Long boardId,
                                         @PathVariable Long cardId) {

        Board board = boardService.getBoardById(boardId, userPrincipal.getId());

        List<BoardListDTO> boardListsDTO = boardMapper.mapToBoardDTO(board).getLists();

        List<List<String>> listsOfCardsIds = boardListsDTO.stream()
                .map(v -> v.getIdCards()
                        .stream()
                        .filter(s -> !s.equals(cardId.toString()))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        cardService.removeCard(listsOfCardsIds, userPrincipal.getId(), boardId, cardId);

        BoardDTO result = boardMapper.mapToBoardDTO(board);

        return result.getLists();
    }

    @PostMapping(value = "/card/update",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CardDTO updateCard(@RequestBody CardDTO cardDTO) {
        Card card = cardMapper.mapToCard(cardDTO);

        Card result = cardService.updateCard(card);

        return cardMapper.mapToCardDTO(result);
    }

    @PostMapping(value = "/card/newCheckList/name={name}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CheckListDTO newCheckList(@PathVariable String name) {
        CheckList checkList = checkListService.newCheckList(name);

        return new CheckListDTO(
                checkList.getId(),
                checkList.getName(),
                checkList.isDeleted(),
                new ArrayList<>()
        );
    }

    @PostMapping(value = "/card/newCheckItem/name={name}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CheckItemDTO newCheckItem(@PathVariable String name) {
        CheckItem checkItem = checkItemService.newCheckItem(name);

        return new CheckItemDTO(
                checkItem.getId(),
                checkItem.getName(),
                checkItem.isChecked(),
                checkItem.isDeleted()
        );
    }

    @PostMapping(value = "/card/{cardId}/newActivity/message={message}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ActivityDTO newActivity(@PathVariable Long cardId,
                            @PathVariable String message) {
        Activity activity = activityService.newActivity(cardId, message);

        return activityMapper.mapToActivityDTO(activity);
    }

    @PostMapping(value = "/{boardId}/card/newLabel/name={name}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    LabelDTO newLabel(@PathVariable Long boardId,
                      @PathVariable String name) {
        Label label = labelService.newLabel(boardId, name);

        return labelMapper.mapToLabelDTO(label);
    }

    @PutMapping(value = "/lists/reorder",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ApiResponse reorderBoardLists(@RequestBody BoardDTO boardDTO) {
        List<BoardListDTO> boardListsDTO = boardDTO.getLists();

        List<BoardList> boardLists = boardListMapper.mapToBoardLists(boardListsDTO);

        boardListService.reorderBoardList(boardLists);

        return new ApiResponse(true, "List order saved. ");
    }

    @PutMapping(value = "/{boardId}/list/{listId}/listTitle={listTitle}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse renameBoardList(@CurrentUser UserPrincipal userPrincipal,
                                       @PathVariable Long boardId,
                                       @PathVariable Long listId,
                                       @PathVariable String listTitle) {

        boardListService.renameBoardList(boardId, userPrincipal.getId(), listId, listTitle);

        return new ApiResponse(true, "List renamed. ");
    }

    @PutMapping(value = "/{boardId}/list/{listId}/delete",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse deleteBoardList(@CurrentUser UserPrincipal userPrincipal,
                                       @PathVariable Long boardId,
                                       @PathVariable Long listId) {

        boardListService.deleteBoardList(boardId, userPrincipal.getId(), listId);

        return new ApiResponse(true, "List deleted. ");
    }
}
