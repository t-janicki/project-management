package com.utility.dto.scrumboard;

public class TeamInfoDTO {
    private Long id;
    private String displayName;
    private String description;
    private String ownerEmail;

    public TeamInfoDTO() {
    }

    public TeamInfoDTO(Long id, String displayName, String description, String ownerEmail) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.ownerEmail = ownerEmail;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }
}
