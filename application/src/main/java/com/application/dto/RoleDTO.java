package com.application.dto;

import com.account.domain.RoleName;

public final class RoleDTO {
    private RoleName name;

    public RoleDTO(RoleName name) {
        this.name = name;
    }

    public RoleName getName() {
        return name;
    }

}
