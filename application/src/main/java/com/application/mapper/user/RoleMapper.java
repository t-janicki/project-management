package com.application.mapper.user;

import com.account.domain.RoleName;
import com.account.domain.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public final class RoleMapper {

    public static String mapRoleToString(RoleName roleName) {
        switch (roleName) {
            case ROLE_MANAGER:
                return "Manager";
            case ROLE_ADMIN:
                return "Admin";
            case ROLE_EMPLOYEE:
                return "Employee";
            case ROLE_USER:
                return "User";
        }
        return "RoleNotExists";
    }


    public static String roleToString(User user) {
        return user.getRoles().stream()
                .map(r -> RoleMapper.mapRoleToString(r.getName()))
                .collect(Collectors.joining());
    }

}
