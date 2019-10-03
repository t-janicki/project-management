package com.application.mapper.scrumboard;

import com.scrumboard.domain.BoardList;
import com.utility.dto.scrumboard.BoardListDTO;
import org.springframework.stereotype.Component;

@Component
public final class BoardListMapper {

    public BoardListDTO mapToBoardListDTO(BoardList boardList) {
        return new BoardListDTO(
                boardList.getId(),
                boardList.getName(),
                boardList.getCardsIds().split(", ")
        );
    }
}
