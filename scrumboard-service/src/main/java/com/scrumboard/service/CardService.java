package com.scrumboard.service;

import com.scrumboard.domain.Card;

import java.util.List;

public interface CardService {

    Card getCardById(Long cardId);

    Card createNewCard(Long boardId, Long userId, String cardTitle, Long listId);

    Card updateCard(Card Card);

    Card removeCard(List<List<String>> cardsLists, Long userId, Long boardId, Long cardId);
}
