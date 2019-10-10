package com.scrumboard.domain;

import javax.persistence.*;

@Entity
@Table(name = "board_lists")
public class BoardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String cardsIds;

    @Column
    private Integer position;

    @Column
    private Boolean isDeleted;

    public BoardList() {
    }

    public BoardList(Long id, String name, String cardsIds) {
        this.id = id;
        this.name = name;
        this.cardsIds = cardsIds;
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

    public String getCardsIds() {
        return cardsIds;
    }

    public void setCardsIds(String cardsIds) {
        this.cardsIds = cardsIds;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
