package com.scrumboard.service;

import com.scrumboard.domain.BoardList;

public interface BoardListService {

    BoardList newBoardList(Long boardId, String listTitle);
}
