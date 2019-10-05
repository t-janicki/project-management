package com.scrumboard.service.impl;

import com.scrumboard.domain.*;
import com.scrumboard.repository.BoardListRepository;
import com.scrumboard.repository.CardRepository;
import com.scrumboard.service.BoardService;
import com.scrumboard.service.CardService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardServiceImpl implements CardService {
    private BoardService boardService;
    private CardRepository cardRepository;
    private BoardListRepository boardListRepository;

    @Autowired
    public CardServiceImpl(BoardService boardService,
                           CardRepository cardRepository,
                           BoardListRepository boardListRepository) {
        this.boardService = boardService;
        this.cardRepository = cardRepository;
        this.boardListRepository = boardListRepository;
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

//    public Card updateCard(Card card) {
//
//    }
}
