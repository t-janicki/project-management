package com.utility.dto.scrumboard;

public class BoardListDTO {
    private Long id;
    private String name;
    private String[] idCards;

    public BoardListDTO() {
    }

    public BoardListDTO(Long id, String name, String[] idCards) {
        this.id = id;
        this.name = name;
        this.idCards = idCards;
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

    public String[] getIdCards() {
        return idCards;
    }

    public void setIdCards(String[] idCards) {
        this.idCards = idCards;
    }
}
