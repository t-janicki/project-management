package com.scrumboard.service.impl;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardList;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.service.BoardListService;
import com.scrumboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardListServiceImpl implements BoardListService {
    private BoardService boardService;
    private BoardRepository boardRepository;

    @Autowired
    public BoardListServiceImpl(BoardService boardService,
                                BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }

    public BoardList newBoardList(Long boardId, String name) {
        Board board = boardService.getBoardById(boardId);

        BoardList boardList = new BoardList();

        boardList.setCardsIds("0");
        boardList.setName(name);

        board.getLists().add(boardList);

        boardRepository.save(board);

        return boardList;
    }
}
