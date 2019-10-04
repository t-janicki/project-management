package com.utility.dto.scrumboard;

import java.util.List;

public class BoardListDTO {
    private Long id;
    private String name;
    private List<String> idCards;

    public BoardListDTO() {
    }

    public BoardListDTO(Long id, String name, List<String> idCards) {
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

    public List getIdCards() {
        return idCards;
    }

    public void setIdCards(List<String> idCards) {
        this.idCards = idCards;
    }
}
