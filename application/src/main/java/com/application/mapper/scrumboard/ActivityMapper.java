package com.application.mapper.scrumboard;

import com.scrumboard.domain.Activity;
import com.utility.dto.scrumboard.ActivityDTO;
import org.springframework.stereotype.Component;

@Component
public final class ActivityMapper {

    public Activity mapToActivity(ActivityDTO activityDTO) {
        return new Activity(
                activityDTO.getId(),
                activityDTO.getType(),
                activityDTO.getMemberId(),
                activityDTO.getMessage(),
                activityDTO.getTime()
        );
    }

    public ActivityDTO mapToActivityDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getType(),
                activity.getMemberId(),
                activity.getMessage(),
                activity.getTime()
        );
    }
}
