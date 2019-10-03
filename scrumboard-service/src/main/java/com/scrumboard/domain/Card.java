package com.scrumboard.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String dueDate;

    @Column
    private String idAttachmentCover;

    @Column
    private String membersIds;

    @Column
    private String labelsIds;

    @Column
    private Boolean isSubscribed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckList> checkLists;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;

    public Card() {
    }

    public Card(String name, String description, String dueDate,
                String idAttachmentCover, String membersIds, String labelsIds,
                Boolean isSubscribed, List<Attachment> attachments,
                List<CheckList> checkLists, List<Activity> activities) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.idAttachmentCover = idAttachmentCover;
        this.membersIds = membersIds;
        this.labelsIds = labelsIds;
        this.isSubscribed = isSubscribed;
        this.attachments = attachments;
        this.checkLists = checkLists;
        this.activities = activities;
    }

    public Long getId() {
        return id;
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

    public String getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(String membersIds) {
        this.membersIds = membersIds;
    }

    public String getLabelsIds() {
        return labelsIds;
    }

    public void setLabelsIds(String labelsIds) {
        this.labelsIds = labelsIds;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        isSubscribed = subscribed;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
