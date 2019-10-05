package com.scrumboard.service;

import com.scrumboard.domain.Board;
import com.scrumboard.domain.Card;
import com.utility.dto.scrumboard.CardDTO;

public interface CardService {

    Card createNewCard(Long boardId, String cardTitle, Long listId);

//    void addCardIdToBoardList(Long cardId, Long listId);

//    Card updateCard(CardDTO cardDTO);

}
