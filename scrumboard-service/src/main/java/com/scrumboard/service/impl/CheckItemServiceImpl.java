package com.scrumboard.service.impl;

import com.scrumboard.domain.CheckItem;
import com.scrumboard.repository.CheckItemRepository;
import com.scrumboard.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    private CheckItemRepository checkItemRepository;

    @Autowired
    public CheckItemServiceImpl(CheckItemRepository checkItemRepository) {
        this.checkItemRepository = checkItemRepository;
    }

    public CheckItem newCheckItem(String name) {
        CheckItem checkItem = new CheckItem();
        checkItem.setDeleted(Boolean.FALSE);
        checkItem.setChecked(Boolean.FALSE);
        checkItem.setName(name);

        return checkItemRepository.save(checkItem);
    }
}
