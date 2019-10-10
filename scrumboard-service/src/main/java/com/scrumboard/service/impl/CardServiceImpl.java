package com.scrumboard.service.impl;

import com.scrumboard.domain.*;
import com.scrumboard.repository.BoardListRepository;
import com.scrumboard.repository.CardRepository;
import com.scrumboard.service.BoardService;
import com.scrumboard.service.CardService;
import com.scrumboard.service.CheckListService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private BoardService boardService;
    private CardRepository cardRepository;
    private BoardListRepository boardListRepository;
    private CheckListService checkListService;

    @Autowired
    public CardServiceImpl(BoardService boardService,
                           CardRepository cardRepository,
                           BoardListRepository boardListRepository,
                           CheckListService checkListService) {
        this.boardService = boardService;
        this.cardRepository = cardRepository;
        this.boardListRepository = boardListRepository;
        this.checkListService = checkListService;
    }

    public Card createNewCard(Long boardId, String cardTitle, Long listId) {
        Card card = new Card();

        card.setName(cardTitle);
        card.setDescription("");
        card.setDueDate("");
        card.setIdAttachmentCover("");
        card.setMembersIds("");
        card.setLabelsIds("");
        card.setSubscribed(true);
        card.setAttachments(new ArrayList<>());
        card.setCheckLists(new ArrayList<>());
        card.setActivities(new ArrayList<>());

        cardRepository.save(card);

        boardService.addCardToBoard(boardId, card);
        addCardIdToBoardList(card.getId(), listId);

        return card;
    }

    private void addCardIdToBoardList(Long cardId, Long listId) {
        BoardList boardList = boardListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException("Board list not found"));

        String idCards = boardList.getCardsIds();

        if (idCards.isEmpty()) {
            idCards = cardId.toString();
        } else {
            idCards = idCards + ", " + cardId.toString();
        }

        boardList.setCardsIds(idCards);

        boardListRepository.save(boardList);
    }

    public Card updateCard(Card request) {
        Card card = cardRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Card not found"));

        System.out.println("labels ids");
        System.out.println(request.getLabelsIds());
        card.setName(request.getName());
        card.setDescription(request.getDescription());
        card.setDueDate(request.getDueDate());
        card.setIdAttachmentCover(request.getIdAttachmentCover());
        card.setMembersIds(request.getMembersIds());
        card.setLabelsIds(request.getLabelsIds());
        card.setSubscribed(request.getSubscribed());

        card.getAttachments().forEach(attachment -> {

            attachment.setName(request.getAttachments().stream()
                    .map(Attachment::getName)
                    .toString());

            attachment.setSrc(request.getAttachments().stream()
                    .map(Attachment::getSrc)
                    .toString());

            attachment.setTime(request.getAttachments().stream()
                    .map(Attachment::getTime)
                    .toString());

            attachment.setType(request.getAttachments().stream()
                    .map(Attachment::getType)
                    .toString());
        });

        checkListService.updateChecklist(card, request);

        card.getActivities().forEach(activity -> {
            activity.setType(request.getActivities().stream()
                    .map(Activity::getType)
                    .toString());

            activity.setMemberId(request.getActivities().stream()
                    .map(Activity::getMemberId)
                    .toString());

            activity.setMessage(request.getActivities().stream()
                    .map(Activity::getMessage)
                    .toString());

            activity.setTime(request.getActivities().stream()
                    .map(Activity::getTime)
                    .toString());

        });

        cardRepository.save(card);

        return card;
    }
}

