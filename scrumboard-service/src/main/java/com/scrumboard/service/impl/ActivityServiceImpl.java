package com.scrumboard.service.impl;

import com.scrumboard.domain.Activity;
import com.scrumboard.domain.Card;
import com.scrumboard.repository.ActivityRepository;
import com.scrumboard.repository.CardRepository;
import com.scrumboard.service.ActivityService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;
    private CardRepository cardRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository,
                               CardRepository cardRepository) {
        this.activityRepository = activityRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Activity newActivity(Long cardId, String message) {
        Activity activity = new Activity();
        activity.setMessage(message);
        activity.setType("comment");
        //IMPLEMENT ADD CURRENT USER ID
        activity.setMemberId("");
        activity.setTime(System.currentTimeMillis());

        activityRepository.save(activity);

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Card not found"));

        card.getActivities().add(activity);

        cardRepository.save(card);

        return activity;
    }
}
