package com.scrumboard.service.impl;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardSettings;
import com.scrumboard.domain.Label;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.service.BoardService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createNewEmptyBoard() {
        Board board = new Board();

        Label sampleLabel1 = new Label("High Priority", "bd-red text-white");
        Label sampleLabel2 = new Label("Design", "bg-orange text-white");
        Label sampleLabel3 = new Label("App", "bd-blue text-white");
        Label sampleLabel4 = new Label("Feature", "bd-green text-white");

        board.setName("Untitled Board");
        board.setUri("untitled-board");
        board.setBoardSettings(new BoardSettings("", true, false));
        board.setLists(new ArrayList<>());
        board.setCards(new ArrayList<>());
        board.setMembers(new ArrayList<>());
        board.setLabels(Arrays.asList(sampleLabel1, sampleLabel2, sampleLabel3, sampleLabel4));

        boardRepository.save(board);

        return board;
    }


    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Board not found with id: " + id));
    }

    public void deleteAllBoards() {
        boardRepository.deleteAll();
    }
}
