package com.application.mapper.scrumboard;

import com.scrumboard.domain.BoardList;
import com.utility.dto.scrumboard.BoardListDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public final class BoardListMapper {

    public BoardListDTO mapToBoardListDTO(BoardList boardList) {
        String[] cardsIds =  boardList.getCardsIds().split(", ");

        List<String> result = Arrays.asList(cardsIds);

        if (boardList.getCardsIds().isEmpty()) {
            result = Collections.emptyList();
        }

        return new BoardListDTO(
                boardList.getId(),
                boardList.getName(),
                result
        );
    }
}
