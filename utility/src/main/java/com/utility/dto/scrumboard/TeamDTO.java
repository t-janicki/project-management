package com.utility.dto.scrumboard;

import java.util.List;

public class TeamDTO {
//    private Long id;
//    private String displayName;
//    private String description;
//    private Long ownerId;
    private TeamInfoDTO teamInfo;
    private List<MemberDTO> members;
    private List<BoardDTO> boards;

    public TeamDTO() {
    }

    public TeamDTO(TeamInfoDTO teamInfo, List<MemberDTO> members, List<BoardDTO> boards) {
        this.teamInfo = teamInfo;
        this.members = members;
        this.boards = boards;
    }

    public TeamInfoDTO getTeamInfo() {
        return teamInfo;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public List<BoardDTO> getBoards() {
        return boards;
    }
}
