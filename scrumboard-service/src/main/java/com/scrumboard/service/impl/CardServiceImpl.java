package com.scrumboard.service.impl;

import com.scrumboard.domain.*;
import com.scrumboard.repository.BoardRepository;
import com.scrumboard.repository.CardRepository;
import com.scrumboard.service.BoardService;
import com.scrumboard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CardServiceImpl implements CardService {
    private BoardService boardService;
    private BoardRepository boardRepository;
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(BoardService boardService,
                           BoardRepository boardRepository,
                           CardRepository cardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
        this.cardRepository = cardRepository;
    }

    public Card createNewCard(Long boardId, String cardTitle) {

        CheckItem checkItem = new CheckItem();
        checkItem.setChecked(Boolean.FALSE);

        CheckList checkList = new CheckList();
        checkList.setCheckItems(Collections.singletonList(checkItem));

        Card card = new Card();

        card.setName(cardTitle);
        card.setDescription("");
        card.setDueDate("");
        card.setIdAttachmentCover("");
        card.setMembersIds("");
        card.setLabelsIds("");
        card.setSubscribed(true);
        card.setAttachments(new ArrayList<>());
        card.setCheckLists(Collections.singletonList(checkList));
        card.setActivities(new ArrayList<>());

        cardRepository.save(card);

        addCardToBoard(boardId, card);

        return card;
    }

    private void addCardToBoard(Long boardId, Card card) {
        Board board = boardService.getBoardById(boardId);

        board.getCards().add(card);

        boardRepository.save(board);
    }
}
