package com.application.mapper.scrumboard;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.BoardType;
import com.utility.dto.scrumboard.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class BoardMapper {
    private BoardSettingsMapper boardSettingsMapper;
    private BoardListMapper boardListMapper;
    private CardMapper cardMapper;
    private LabelMapper labelMapper;

    @Autowired
    public BoardMapper(BoardSettingsMapper boardSettingsMapper,
                       BoardListMapper boardListMapper,
                       CardMapper cardMapper,
                       LabelMapper labelMapper) {
        this.boardSettingsMapper = boardSettingsMapper;
        this.boardListMapper = boardListMapper;
        this.cardMapper = cardMapper;
        this.labelMapper = labelMapper;
    }

    public BoardDTO mapToBoardDTO(Board board) {
        return new BoardDTO(
                board.getId(),
                board.getName(),
                board.getDescription(),
                board.getUri(),
                mapBoardTypeToString(board.getBoardType()),
                boardSettingsMapper.mapToBoardSettingsDTO(board.getBoardSettings()),
                board.getLists().stream()
                        .map(v -> boardListMapper.mapToBoardListDTO(v))
                        .collect(Collectors.toList()),
                board.getCards().stream()
                        .map(v -> cardMapper.mapToCardDTO(v))
                        .collect(Collectors.toList()),
                board.getLabels().stream()
                        .map(v -> labelMapper.mapToLabelDTO(v))
                        .collect(Collectors.toList())
        );
    }

    public static String mapBoardTypeToString(BoardType boardType) {
        switch (boardType) {
            case PERSONAL:
                return "PERSONAL";
            case TEAM:
                return "TEAM";
        }
        return "Incorrect board type";
    }

    public List<BoardDTO> mapToBoardDTOList(List<Board> boards) {
        return boards.stream()
                .map(this::mapToBoardDTO)
                .collect(Collectors.toList());
    }
}
