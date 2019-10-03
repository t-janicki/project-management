package com.utility.dto.scrumboard;

import java.util.List;

public class CardDTO {
    private Long id;
    private String name;
    private String description;
    private String dueDate;
    private String idAttachmentCover;
    private String[] idMembers;
    private String[] idLabels;
    private Boolean subscribed;
    private List<AttachmentDTO> attachments;
    private List<CheckListDTO> checklists;
    private List<ActivityDTO> activities;

    public CardDTO() {
    }

    public CardDTO(Long id, String name, String description,
                   String dueDate, String idAttachmentCover,
                   String[] idMembers, String[] idLabels,
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIdAttachmentCover() {
        return idAttachmentCover;
    }

    public void setIdAttachmentCover(String idAttachmentCover) {
        this.idAttachmentCover = idAttachmentCover;
    }

    public String[] getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(String[] idMembers) {
        this.idMembers = idMembers;
    }

    public String[] getIdLabels() {
        return idLabels;
    }

    public void setIdLabels(String[] idLabels) {
        this.idLabels = idLabels;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }

    public List<CheckListDTO> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<CheckListDTO> checklists) {
        this.checklists = checklists;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }
}

