package com.scrumboard.service.impl;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.Label;
import com.scrumboard.domain.LabelColor;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.repository.LabelRepository;
import com.scrumboard.service.LabelService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<LabelColor> labelColors = Arrays.asList(LabelColor.values());
        Collections.shuffle(labelColors);
        String labelColor = labelColors.stream().findFirst().get().color;

        Label label = new Label();
        label.setName(name);
        label.setClassName(labelColor);

        labelRepository.save(label);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("Board not found"));

        board.getLabels().add(label);

        boardRepository.save(board);

        return label;
    }
}
