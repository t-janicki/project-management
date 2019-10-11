package com.scrumboard.service;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.Card;

import java.util.List;

public interface BoardService {

    Board createNewEmptyBoard();

    List<Board> getBoards();

    Board getBoardById(Long id);

    Board renameBoard(Long boardId, String name);

    void deleteAllBoards();

    void addCardToBoard(Long boardId, Card card);
}
