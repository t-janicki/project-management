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

    @Override
    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Card not found"));
    }

    public Card createNewCard(Long boardId, Long userId, String cardTitle, Long listId) {
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

        boardService.addCardToBoard(boardId, userId, card);
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

        card.setName(request.getName());
        card.setDescription(request.getDescription());
        card.setDueDate(request.getDueDate());
        card.setIdAttachmentCover(request.getIdAttachmentCover());
        card.setMembersIds(request.getMembersIds());
        card.setLabelsIds(request.getLabelsIds());
        card.setSubscribed(request.getSubscribed());

        checkListService.updateChecklist(card, request);

        cardRepository.save(card);

        return card;
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }
}

