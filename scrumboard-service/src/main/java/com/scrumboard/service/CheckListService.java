package com.scrumboard.service;

import com.scrumboard.domain.Card;
import com.scrumboard.domain.CheckList;

public interface CheckListService {

    void updateChecklist(Card current, Card request);

    CheckList newCheckList(String name);
}
