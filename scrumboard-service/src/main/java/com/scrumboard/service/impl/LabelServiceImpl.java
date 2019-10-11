package com.scrumboard.service.impl;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.Label;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.repository.LabelRepository;
import com.scrumboard.service.LabelService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {
    private LabelRepository labelRepository;
    private BoardRepository boardRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository,
                            BoardRepository boardRepository) {
        this.labelRepository = labelRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public Label newLabel(Long boardId, String name) {
        Label label = new Label();
        label.setName(name);
        label.setClassName("bg-orange-dark");

        labelRepository.save(label);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("Board not found"));

        board.getLabels().add(label);

        boardRepository.save(board);

        return label;
    }
}
