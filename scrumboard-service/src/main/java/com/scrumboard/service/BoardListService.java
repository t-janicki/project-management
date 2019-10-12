package com.scrumboard.service;

import com.scrumboard.domain.BoardList;

import java.util.List;

public interface BoardListService {

    BoardList newBoardList(Long boardId, Long userId, String listTitle);

    List<BoardList> reorderBoardList(List<BoardList> boardList);

    BoardList renameBoardList(Long boardId, Long userId, Long listId, String listTitle);

    BoardList deleteBoardList(Long boardId, Long userId, Long listId);

    BoardList getBoardListById(Long boardListId);
}
