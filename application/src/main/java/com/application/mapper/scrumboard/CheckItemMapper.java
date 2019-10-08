package com.application.mapper.scrumboard;

import com.scrumboard.domain.CheckItem;
import com.utility.dto.scrumboard.CheckItemDTO;
import org.springframework.stereotype.Component;

@Component
public final class CheckItemMapper {

    public CheckItem mapToCheckItem(CheckItemDTO checkItemDTO) {
        return new CheckItem(
                checkItemDTO.getId(),
                checkItemDTO.getName(),
                checkItemDTO.getChecked(),
                checkItemDTO.isDeleted()
        );
    }

    public CheckItemDTO mapToCheckItemDTO(CheckItem checkItem) {
        return new CheckItemDTO(
                checkItem.getId(),
                checkItem.getName(),
                checkItem.isChecked(),
                checkItem.isDeleted()
        );
    }
}
