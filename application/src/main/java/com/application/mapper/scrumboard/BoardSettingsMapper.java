package com.application.mapper.scrumboard;

import com.scrumboard.domain.BoardSettings;
import com.utility.dto.scrumboard.BoardSettingsDTO;
import org.springframework.stereotype.Component;

@Component
public final class BoardSettingsMapper {

    public BoardSettings mapToBoardSettings(BoardSettingsDTO boardSettingsDTO) {
        return new BoardSettings(
                boardSettingsDTO.getId(),
                boardSettingsDTO.getColor(),
                boardSettingsDTO.getSubscribed(),
                boardSettingsDTO.getCardCoverImages()
        );
    }

    public BoardSettingsDTO mapToBoardSettingsDTO(BoardSettings boardSettings) {
        return new BoardSettingsDTO(
                boardSettings.getId(),
                boardSettings.getColor(),
                boardSettings.getSubscribed(),
                boardSettings.getCardCoverImages()
        );
    }
}
