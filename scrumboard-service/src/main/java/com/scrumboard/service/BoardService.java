package com.scrumboard.service;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardSettings;
import com.scrumboard.domain.BoardType;
import com.scrumboard.domain.Card;

import java.util.List;

public interface BoardService {

    Board createNewEmptyBoard(Long userId, String name, BoardType boardType, String description, Long teamId);

    List<Board> getBoards(Long userId, BoardType boardType);

    Board getBoardByIdAndUserId(Long boardId, Long userId, BoardType boardType);

    Board renameBoard(Long boardId, Long userId, String name, BoardType boardType);

    BoardSettings updateSettings(Long boardId, Long userId, BoardSettings boardSettings, BoardType boardType);

    void deleteBoardById(Long boardId, Long userId, BoardType boardType);

    void deleteAllBoards();

    void addCardToBoard(Long boardId, Long userId, Card card, BoardType boardType);

    Board removeCard(Long boardId, Long userId, Card card, BoardType boardType);

    Board reorderCards(List<List<String>> cardsLists, Long userId, Long boardId, BoardType boardType);

}
