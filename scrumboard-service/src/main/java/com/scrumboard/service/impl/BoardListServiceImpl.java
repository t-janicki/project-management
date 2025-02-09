package com.scrumboard.service.impl;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardList;
import com.scrumboard.domain.BoardType;
import com.scrumboard.repository.BoardListRepository;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.service.BoardListService;
import com.scrumboard.service.BoardService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardListServiceImpl implements BoardListService {
    private BoardService boardService;
    private BoardRepository boardRepository;
    private BoardListRepository boardListRepository;

    @Autowired
    public BoardListServiceImpl(BoardService boardService,
                                BoardRepository boardRepository,
                                BoardListRepository boardListRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
        this.boardListRepository = boardListRepository;
    }

    public BoardList newBoardList(Long boardId, Long userId, String name, BoardType boardType) {
        Board board = boardService.getBoardByIdAndUserId(boardId, userId, boardType);

        BoardList boardList = new BoardList();

        int position = board.getLists().size();

        boardList.setCardsIds("");
        boardList.setName(name);
        boardList.setPosition(position);

        board.getLists().add(boardList);

        boardRepository.save(board);

        return boardList;
    }

    public List<BoardList> reorderBoardList(List<BoardList> boardLists) {
        final int[] position = {0};

        boardLists.forEach(v -> {
            v.setPosition(position[0]++);
        });

        return boardListRepository.saveAll(boardLists);


    }

    public BoardList renameBoardList(Long boardId, Long userId, Long listId,
                                     String listTitle, BoardType boardType) {
        Board board = boardService.getBoardByIdAndUserId(boardId, userId, boardType);

        BoardList boardList = board.getLists().stream().filter(bl -> bl.getId().equals(listId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Board List not found"));

        boardList.setName(listTitle);

        return boardListRepository.save(boardList);
    }

    @Override
    public BoardList deleteBoardList(Long boardId, Long userId,
                                     Long listId, BoardType boardType) {
        Board board = boardService.getBoardByIdAndUserId(boardId, userId, boardType);

        BoardList boardList = getBoardListById(listId);
        boardList.setDeleted(Boolean.TRUE);

        board.getLists().remove(boardList);

        boardRepository.save(board);

        return boardListRepository.save(boardList);
    }

    @Override
    public BoardList getBoardListById(Long boardListId) {
        return boardListRepository.findById(boardListId)
                .orElseThrow(() -> new NotFoundException("Board List not found"));
    }
}
