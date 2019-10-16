package com.application.mapper.scrumboard;

import com.scrumboard.domain.Board;
import com.utility.dto.scrumboard.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class BoardMapper {
    private BoardSettingsMapper boardSettingsMapper;
    private BoardListMapper boardListMapper;
    private CardMapper cardMapper;
    private LabelMapper labelMapper;
    private TeamMapper teamMapper;

    @Autowired
    public BoardMapper(BoardSettingsMapper boardSettingsMapper,
                       BoardListMapper boardListMapper,
                       CardMapper cardMapper,
                       LabelMapper labelMapper,
                       TeamMapper teamMapper) {
        this.boardSettingsMapper = boardSettingsMapper;
        this.boardListMapper = boardListMapper;
        this.cardMapper = cardMapper;
        this.labelMapper = labelMapper;
        this.teamMapper = teamMapper;
    }

    public BoardDTO mapToBoardDTO(Board board) {
        return new BoardDTO(
                board.getId(),
                board.getName(),
                board.getUri(),
                boardSettingsMapper.mapToBoardSettingsDTO(board.getBoardSettings()),
                board.getLists().stream()
                        .map(v -> boardListMapper.mapToBoardListDTO(v))
                        .collect(Collectors.toList()),
                board.getCards().stream()
                        .map(v -> cardMapper.mapToCardDTO(v))
                        .collect(Collectors.toList()),
//                teamMapper.mapToTeamDTO(board.getTeam()),
                board.getLabels().stream()
                        .map(v -> labelMapper.mapToLabelDTO(v))
                        .collect(Collectors.toList())
        );
    }

    public List<BoardDTO> mapToBoardDTOList(List<Board> boards) {
        return boards.stream()
                .map(this::mapToBoardDTO)
                .collect(Collectors.toList());
    }
}
