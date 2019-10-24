package com.scrumboard.service.impl;

import com.scrumboard.domain.*;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.service.BoardService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createNewEmptyPersonalBoard(Long userId, String name, BoardType boardType, String description) {
        Board board = new Board();

        String uri = name.replaceAll(" ","-").toLowerCase();

        board.setName(name);
        board.setDescription(description);
        board.setUri(uri);
        board.isDeleted(Boolean.FALSE);
        board.setUserId(userId);
        board.setBoardSettings(new BoardSettings("", false, true));
        board.setLists(new ArrayList<>());
        board.setCards(new ArrayList<>());
        board.setBoardType(boardType);
        board.setLabels(defaultLabels());

        boardRepository.save(board);

        return board;
    }

    private List<Label> defaultLabels() {
        Label sampleLabel1 = new Label("High Priority", "bg-red text-white");
        Label sampleLabel2 = new Label("Design", "bg-orange text-white");
        Label sampleLabel3 = new Label("App", "bg-blue text-white");
        Label sampleLabel4 = new Label("Feature", "bg-green text-white");

        return new ArrayList<>(Arrays.asList(sampleLabel1, sampleLabel2, sampleLabel3, sampleLabel4));
    }

    public List<Board> getBoards(Long userId, BoardType boardType) {
//        return boardRepository.findAllByIsDeletedIsFalseAndUserId(userId);
        return boardRepository.findAllByUserIdAndBoardTypeAndIsDeletedIsFalse(userId, boardType);
    }

    public Board getBoardByIdAndUserId(Long boardId, Long userId, BoardType boardType) {
        Board board = boardRepository.findByIdAndIsDeletedIsFalseAndUserIdAndBoardType(boardId, userId, boardType)
                .orElseThrow(() -> new NotFoundException("Board not found with id: " + boardId));

//        Board board = boardRepository.findById(boardId).get();

        board.getLists().sort(Comparator.comparing(BoardList::getPosition));

        return board;
    }

    public Board renameBoard(Long boardId, Long userId, String name, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);

        String uri = name.replaceAll(" ","-");

        board.setName(name);
        board.setUri(uri.toLowerCase());

        return boardRepository.save(board);
    }

    @Override
    public BoardSettings updateSettings(Long boardId, Long userId, BoardSettings settings, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);

        board.getBoardSettings().setCardCoverImages(settings.getCardCoverImages());
        board.getBoardSettings().setColor(settings.getColor());
        board.getBoardSettings().setSubscribed(settings.getSubscribed());

        boardRepository.save(board);

        return board.getBoardSettings();
    }

    public void deleteBoardById(Long boardId, Long userId, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);

        board.isDeleted(Boolean.TRUE);

        boardRepository.save(board);
    }

    public void deleteAllBoards() {
        boardRepository.deleteAll();
    }

    public void addCardToBoard(Long boardId, Long userId, Card card, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);

        board.getCards().add(card);

        boardRepository.save(board);
    }

    public Board removeCard(Long boardId, Long userId, Card card, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);
        board.getCards().remove(card);

        return boardRepository.save(board);
    }

    public Board reorderCards(List<List<String>> cardsLists, Long userId, Long boardId, BoardType boardType) {
        Board board = getBoardByIdAndUserId(boardId, userId, boardType);

        int i = 0;
        for (List<String> ids : cardsLists) {

            String cardsIds = String.join(", ", ids);

            board.getLists().get(i).setCardsIds(cardsIds);

            i++;
        }

        return boardRepository.save(board);
    }
}
