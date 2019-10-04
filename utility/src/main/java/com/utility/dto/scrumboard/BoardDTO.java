package com.utility.dto.scrumboard;

import java.util.List;

public class BoardDTO {
    private Long id;
    private String name;
    private String uri;
    private BoardSettingsDTO settings;
    private List<BoardListDTO> lists;
    private List<CardDTO> cards;
    private List<MemberDTO> members;
    private List<LabelDTO> labels;

    public BoardDTO() {
    }

    public BoardDTO(Long id, String name, String uri,
                    BoardSettingsDTO settings, List<BoardListDTO> lists,
                    List<CardDTO> cards, List<MemberDTO> members,
                    List<LabelDTO> labels) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.settings = settings;
        this.lists = lists;
        this.cards = cards;
        this.members = members;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public BoardSettingsDTO getSettings() {
        return settings;
    }

    public List<BoardListDTO> getLists() {
        return lists;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }

}
