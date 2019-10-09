package com.scrumboard.service;

import com.scrumboard.domain.BoardList;

import java.util.List;

public interface BoardListService {

    BoardList newBoardList(Long boardId, String listTitle);

    List<BoardList> reorderBoardList(List<BoardList> boardList);
}
