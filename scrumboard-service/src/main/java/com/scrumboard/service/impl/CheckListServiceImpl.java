package com.scrumboard.service.impl;

import com.scrumboard.domain.Card;
import com.scrumboard.domain.CheckList;
import com.scrumboard.repository.CheckListRepository;
import com.scrumboard.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckListServiceImpl implements CheckListService {
    private CheckListRepository checkListRepository;

    @Autowired
    public CheckListServiceImpl(CheckListRepository checkListRepository) {
        this.checkListRepository = checkListRepository;
    }

    public CheckList newCheckList(String name) {
        CheckList checkList = new CheckList();
        checkList.setDeleted(Boolean.FALSE);
        checkList.setName(name);

        checkListRepository.save(checkList);

        return checkList;
    }

    public void updateChecklist(Card current, Card request) {
        int currentChecklistSize = current.getCheckLists().size();
        int requestChecklistSize = request.getCheckLists().size();

        if (requestChecklistSize == currentChecklistSize) {

            updateExists(request);

        } else if (requestChecklistSize > currentChecklistSize) {

            addChecklist(current, request);

        } else {

            deleteChecklist(current, request);
        }
    }

    private void updateExists(Card request) {
        checkListRepository.saveAll(request.getCheckLists());
    }

    private void addChecklist(Card current, Card request) {
        List<Long> currentChecklistIds = current.getCheckLists().stream()
                .map(CheckList::getId)
                .collect(Collectors.toList());

        List<Long> requestChecklistIds = request.getCheckLists().stream()
                .map(CheckList::getId)
                .collect(Collectors.toList());

        List<Long> result = requestChecklistIds.stream()
                .filter(v -> !currentChecklistIds.contains(v))
                .collect(Collectors.toList());


        List<CheckList> checkLists = checkListRepository.findAllByIdIn(result);

        if (!checkLists.isEmpty()) {
            current.getCheckLists().addAll(checkLists);
        }
    }

    private void deleteChecklist(Card current, Card request) {
        List<Long> currentChecklistIds = current.getCheckLists().stream()
                .map(CheckList::getId)
                .collect(Collectors.toList());

        List<Long> requestChecklistIds = request.getCheckLists().stream()
                .map(CheckList::getId)
                .collect(Collectors.toList());

        List<Long> result = currentChecklistIds.stream()
                .filter(v -> !requestChecklistIds.contains(v))
                .collect(Collectors.toList());

        List<CheckList> checkLists = checkListRepository.findAllByIdIn(result);

        if (!checkLists.isEmpty()) {

            checkLists.forEach(v -> {
                v.setDeleted(Boolean.TRUE);
                checkListRepository.save(v);
            });
        }

        current.getCheckLists().removeAll(checkLists);
    }
}
