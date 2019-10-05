package com.utility.dto.scrumboard;

import java.util.List;

public class CardDTO {
    private Long id;
    private String name;
    private String description;
    private String dueDate;
    private String idAttachmentCover;
    private List<String> idMembers;
    private List<String> idLabels;
    private Boolean subscribed;
    private List<AttachmentDTO> attachments;
    private List<CheckListDTO> checklists;
    private List<ActivityDTO> activities;

    public CardDTO() {
    }

    public CardDTO(Long id, String name, String description,
                   String dueDate, String idAttachmentCover,
                   List<String> idMembers, List<String> idLabels,
                   Boolean subscribed, List<AttachmentDTO> attachments,
                   List<CheckListDTO> checklists, List<ActivityDTO> activities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.idAttachmentCover = idAttachmentCover;
        this.idMembers = idMembers;
        this.idLabels = idLabels;
        this.subscribed = subscribed;
        this.attachments = attachments;
        this.checklists = checklists;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getIdAttachmentCover() {
        return idAttachmentCover;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public List<String> getIdLabels() {
        return idLabels;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public List<CheckListDTO> getChecklists() {
        return checklists;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }
}

