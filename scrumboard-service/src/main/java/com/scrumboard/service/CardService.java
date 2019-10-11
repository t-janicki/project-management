package com.scrumboard.service;

import com.scrumboard.domain.Card;

public interface CardService {

    Card getCardById(Long cardId);

    Card createNewCard(Long boardId, String cardTitle, Long listId);

    Card updateCard(Card Card);

    void deleteCard(Long cardId);
}
