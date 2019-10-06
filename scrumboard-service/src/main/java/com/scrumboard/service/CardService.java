package com.scrumboard.service;

import com.scrumboard.domain.Card;

public interface CardService {

    Card createNewCard(Long boardId, String cardTitle, Long listId);

    Card updateCard(Card Card);

}
