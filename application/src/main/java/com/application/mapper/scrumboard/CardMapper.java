package com.application.mapper.scrumboard;

import com.scrumboard.domain.Card;
import com.scrumboard.domain.Member;
import com.utility.dto.scrumboard.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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

    public Card mapToCard(CardDTO cardDTO) {

        Set<String> setLabelsIds = new HashSet<>(cardDTO.getIdLabels());

        List<String> labels = new ArrayList<>(setLabelsIds);

        String labelsIds = String.join(", ", labels);

        Set<String> setMembersIds = new HashSet<>(cardDTO.getIdMembers());

        List<String> membersId = new ArrayList<>(setMembersIds);

        String membersIds = String.join(", ", membersId);

        return new Card(
                cardDTO.getId(),
                cardDTO.getName(),
                cardDTO.getDescription(),
                cardDTO.getDueDate(),
                cardDTO.getIdAttachmentCover(),
                membersIds,
                labelsIds,
                cardDTO.getSubscribed(),
                cardDTO.getAttachments().stream()
                        .map(v -> attachmentMapper.mapToAttachment(v))
                        .collect(Collectors.toList()),
                cardDTO.getChecklists().stream()
                        .map(v -> checkListMapper.mapToCheckList(v))
                        .collect(Collectors.toList()),
                cardDTO.getActivities().stream()
                        .map(v -> activityMapper.mapToActivity(v))
                        .collect(Collectors.toList())
        );
    }
}
