package com.scrumboard.service;

import com.scrumboard.domain.Board;

import java.util.List;

public interface BoardService {

    Board createNewEmptyBoard();

    List<Board> getBoards();

    Board getBoardById(Long id);

    void deleteAllBoards();
}
