package com.application.mapper.scrumboard;

import com.scrumboard.domain.Card;
import com.utility.dto.scrumboard.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class CardMapper {
    private AttachmentMapper attachmentMapper;
    private CheckListMapper checkListMapper;
    private ActivityMapper activityMapper;

    @Autowired
    public CardMapper(AttachmentMapper attachmentMapper,
                      CheckListMapper checkListMapper,
                      ActivityMapper activityMapper) {
        this.attachmentMapper = attachmentMapper;
        this.checkListMapper = checkListMapper;
        this.activityMapper = activityMapper;
    }

    public CardDTO mapToCardDTO(Card card) {
        String[] membersIds = card.getMembersIds().split(", ");

        List<String> membersIdsResult = Arrays.asList(membersIds);

        if (card.getMembersIds().isEmpty()) {
            membersIdsResult = Collections.emptyList();
        }

        String[] labelsIds = card.getLabelsIds().split(", ");

        List<String> labelsIdsResult = Arrays.asList(labelsIds);

        if (card.getLabelsIds().isEmpty()) {
            labelsIdsResult = Collections.emptyList();
        }

        return new CardDTO(
                card.getId(),
                card.getName(),
                card.getDescription(),
                card.getDueDate(),
                card.getIdAttachmentCover(),
                membersIdsResult,
                labelsIdsResult,
                card.getSubscribed(),
                card.getAttachments().stream()
                        .map(v -> attachmentMapper.mapToAttachmentDTO(v))
                        .collect(Collectors.toList()),
                card.getCheckLists().stream()
                        .map(v -> checkListMapper.mapToCheckListDTO(v))
                        .collect(Collectors.toList()),
                card.getActivities().stream()
                        .map(v -> activityMapper.mapToActivityDTO(v))
                        .collect(Collectors.toList())
        );
    }
}
