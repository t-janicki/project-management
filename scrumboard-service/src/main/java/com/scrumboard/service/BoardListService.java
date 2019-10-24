package com.scrumboard.service;

import com.scrumboard.domain.BoardList;
import com.scrumboard.domain.BoardType;

import java.util.List;

public interface BoardListService {

    BoardList newBoardList(Long boardId, Long userId, String listTitle, BoardType boardType);

    List<BoardList> reorderBoardList(List<BoardList> boardList);

    BoardList renameBoardList(Long boardId, Long userId, Long listId, String listTitle, BoardType boardType);

    BoardList deleteBoardList(Long boardId, Long userId, Long listId, BoardType boardType);

    BoardList getBoardListById(Long boardListId);
}
