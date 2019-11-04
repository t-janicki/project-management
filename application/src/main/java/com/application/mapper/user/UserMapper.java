package com.application.mapper.user;

import com.account.domain.User;
import com.utility.web.response.user.UserInfoResponse;
import org.springframework.stereotype.Component;
import static com.application.mapper.user.RoleMapper.roleToString;

@Component
public class UserMapper {

    public UserInfoResponse mapToUserInfo(User user) {
        return new UserInfoResponse(
                user.getId(),
                roleToString(user),
                user.getFirstName(),
                user.getLastName(),
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
