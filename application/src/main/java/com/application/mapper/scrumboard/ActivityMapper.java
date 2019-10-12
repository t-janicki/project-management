package com.application.mapper.scrumboard;

import com.scrumboard.domain.Activity;
import com.utility.dto.scrumboard.ActivityDTO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public final class ActivityMapper {

    public Activity mapToActivity(ActivityDTO activityDTO) {
        return new Activity(
                activityDTO.getId(),
                activityDTO.getType(),
                activityDTO.getIdMember(),
                activityDTO.getMessage()
        );
    }

    public ActivityDTO mapToActivityDTO(Activity activity) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

        return new ActivityDTO(
                activity.getId(),
                activity.getType(),
                activity.getMemberId(),
                activity.getMessage(),
                formatter.format(activity.getTime())
        );
    }
}
