package com.scrumboard.service;

import com.scrumboard.domain.Card;

public interface CheckListService {

    void updateChecklist(Card current, Card request);
}
