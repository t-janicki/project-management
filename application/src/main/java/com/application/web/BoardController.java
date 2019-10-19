package com.application.web;

import com.account.domain.User;
import com.account.service.UserService;
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
    private UserService userService;
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
    public BoardController(UserService userService,
                           BoardService boardService,
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
        this.userService = userService;
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
        User user = userService.getUserById(userPrincipal.getId());

        Board board = boardService.createNewEmptyPersonalBoard(userPrincipal.getId(), user.getAvatarUrl(), user.getName());

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
        Board board = boardService.getBoardByIdAndUserId(boardId, userPrincipal.getId());

        return boardMapper.mapToBoardDTO(board);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse renameBoard(@CurrentUser UserPrincipal userPrincipal,
                                   @RequestBody BoardDTO boardDTO) {

        boardService.renameBoard(boardDTO.getId(), userPrincipal.getId(), boardDTO.getName());

        return new ApiResponse(true, "Board renamed. ");
    }

    @PutMapping(value = "/settings",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardSettings updateBoardSettings(@CurrentUser UserPrincipal userPrincipal,
                                             @RequestBody BoardDTO boardDTO) {

            BoardSettings boardSettings = boardSettingsMapper.mapToBoardSettings(boardDTO.getSettings());

        return boardService.updateSettings(boardDTO.getId(), userPrincipal.getId(), boardSettings);
    }

    @DeleteMapping(value = "/{boardId}")
    public ApiResponse deleteBoard(@CurrentUser UserPrincipal userPrincipal,
                                   @PathVariable Long boardId) {
        boardService.deleteBoardById(boardId, userPrincipal.getId());

        return new ApiResponse(true, "Board deleted. ");
    }

    @PostMapping(value = "/{boardId}/newList", produces = APPLICATION_JSON_VALUE)
    public List<BoardListDTO> newBoardList(@CurrentUser UserPrincipal userPrincipal,
                                           @PathVariable Long boardId,
                                           @RequestBody BoardListDTO boardListDTO) {

        boardListService.newBoardList(boardId, userPrincipal.getId(), boardListDTO.getName());

        Board board = boardService.getBoardByIdAndUserId(boardId, userPrincipal.getId());

        return boardMapper.mapToBoardDTO(board).getLists();
    }

    @GetMapping(value = "/delete", produces = APPLICATION_JSON_VALUE)
    public void deleteAllBoards() {
        boardService.deleteAllBoards();
    }

    @PostMapping(value = "/{boardId}/list/{listId}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    BoardDTO createNewCard(@CurrentUser UserPrincipal userPrincipal,
                           @PathVariable Long boardId,
                           @PathVariable Long listId,
                           @RequestBody CardDTO cardDTO) {

        cardService.createNewCard(boardId, userPrincipal.getId(), cardDTO.getName(), listId);

        return boardMapper.mapToBoardDTO(boardService.getBoardByIdAndUserId(boardId, userPrincipal.getId()));
    }

    @DeleteMapping(value = "/{boardId}/card/{cardId}")
    public List<BoardListDTO> deleteCard(@CurrentUser UserPrincipal userPrincipal,
                                         @PathVariable Long boardId,
                                         @PathVariable Long cardId) {

        Board board = boardService.getBoardByIdAndUserId(boardId, userPrincipal.getId());

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

    @PutMapping(value = "/card",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CardDTO updateCard(@RequestBody CardDTO cardDTO) {
        Card card = cardMapper.mapToCard(cardDTO);

        Card result = cardService.updateCard(card);

        return cardMapper.mapToCardDTO(result);
    }

    @PostMapping(value = "/card/newCheckList",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CheckListDTO newCheckList(@RequestBody CheckListDTO checkListDTO) {
        CheckList checkList = checkListService.newCheckList(checkListDTO.getName());

        return new CheckListDTO(
                checkList.getId(),
                checkList.getName(),
                checkList.isDeleted(),
                new ArrayList<>()
        );
    }

    @PostMapping(value = "/card/newCheckItem",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    CheckItemDTO newCheckItem(@RequestBody CheckItemDTO checkItemDTO) {
        CheckItem checkItem = checkItemService.newCheckItem(checkItemDTO.getName());

        return new CheckItemDTO(
                checkItem.getId(),
                checkItem.getName(),
                checkItem.isChecked(),
                checkItem.isDeleted()
        );
    }

    @PostMapping(value = "/card/{cardId}/newActivity",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ActivityDTO newActivity(@CurrentUser UserPrincipal userPrincipal,
                            @PathVariable Long cardId,
                            @RequestBody ActivityDTO activityDTO) {
        User user = userService.getUserById(userPrincipal.getId());

        Activity activity = activityService.newActivity(user.getName(), user.getAvatarUrl(), cardId, activityDTO.getMessage());

        return activityMapper.mapToActivityDTO(activity);
    }

    @PostMapping(value = "/{boardId}/card/newLabel",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    LabelDTO newLabel(@PathVariable Long boardId,
                      @RequestBody LabelDTO labelDTO) {
        Label label = labelService.newLabel(boardId, labelDTO.getName());

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

    @PutMapping(value = "/cards/reorder",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse reorderCards(@CurrentUser UserPrincipal userPrincipal,
                                    @RequestBody BoardDTO boardDTO) {

        List<List<String>> listsOfCardsIds = boardDTO.getLists().stream()
                .map(v -> new ArrayList<>(v.getIdCards()))
                .collect(Collectors.toList());

       boardService.reorderCards(listsOfCardsIds, userPrincipal.getId(), boardDTO.getId());

        return new ApiResponse(true, "Cards reordered");
    }

    @PutMapping(value = "/{boardId}/list",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ApiResponse renameBoardList(@CurrentUser UserPrincipal userPrincipal,
                                       @PathVariable Long boardId,
                                       @RequestBody BoardListDTO boardListDTO) {

        boardListService.renameBoardList(boardId, userPrincipal.getId(), boardListDTO.getId(), boardListDTO.getName());

        return new ApiResponse(true, "List renamed. ");
    }

    @DeleteMapping(value = "/{boardId}/list/{listId}")
    public ApiResponse deleteBoardList(@CurrentUser UserPrincipal userPrincipal,
                                       @PathVariable Long boardId,
                                       @PathVariable Long listId) {

        boardListService.deleteBoardList(boardId, userPrincipal.getId(), listId);

        return new ApiResponse(true, "List deleted. ");
    }
}
