package com.utility.dto.scrumboard;

import java.util.List;

public class TeamDTO {
    private Long id;
    private String displayName;
    private String description;
    private Long ownerId;
    private List<MemberDTO> members;

    public TeamDTO() {
    }

    public TeamDTO(Long id, String displayName, String description,
                   Long ownerId, List<MemberDTO> members) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.ownerId = ownerId;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }
}
