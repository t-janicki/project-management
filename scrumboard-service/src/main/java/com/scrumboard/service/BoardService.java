package com.scrumboard.service;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardSettings;
import com.scrumboard.domain.BoardType;
import com.scrumboard.domain.Card;

import java.util.List;

public interface BoardService {

    Board createNewEmptyPersonalBoard(Long userId, String name, BoardType boardType, String description);

    List<Board> getBoards(Long userId);

    Board getBoardByIdAndUserId(Long boardId, Long userId);

    Board renameBoard(Long boardId, Long userId, String name);

    BoardSettings updateSettings(Long boardId, Long userId, BoardSettings boardSettings);

    void deleteBoardById(Long boardId, Long userId);

    void deleteAllBoards();

    void addCardToBoard(Long boardId, Long userId, Card card);

    Board removeCard(Long boardId, Long userId, Card card);

    Board reorderCards(List<List<String>> cardsLists, Long userId, Long boardId);

}
