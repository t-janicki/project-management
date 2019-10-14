package com.scrumboard.service;

import com.scrumboard.domain.Activity;

public interface ActivityService {

    Activity newActivity(String userName, String avatarUrl, Long cardId, String message);
}
