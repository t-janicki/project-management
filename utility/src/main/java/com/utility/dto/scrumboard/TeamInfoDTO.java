package com.utility.dto.scrumboard;

public class TeamInfoDTO {
    private Long id;
    private String displayName;
    private String description;
    private Long ownerId;

    public TeamInfoDTO() {
    }

    public TeamInfoDTO(Long id, String displayName, String description, Long ownerId) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.ownerId = ownerId;
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
}
