package com.scrumboard.service;

import com.scrumboard.domain.Activity;

public interface ActivityService {

    Activity newActivity(Long userId, Long cardId, String message);
}
