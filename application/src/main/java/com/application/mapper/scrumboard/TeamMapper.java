package com.application.mapper.scrumboard;

import com.scrumboard.domain.Team;
import com.utility.dto.scrumboard.TeamDTO;
import com.utility.dto.scrumboard.TeamInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TeamMapper {
    private MemberMapper memberMapper;
    private BoardMapper boardMapper;

    @Autowired
    public TeamMapper(MemberMapper memberMapper,
                      BoardMapper boardMapper) {
        this.memberMapper = memberMapper;
        this.boardMapper = boardMapper;
    }

    public TeamDTO mapToTeamDTO(Team team) {
        TeamInfoDTO teamInfoDTO = new TeamInfoDTO(
                team.getId(),
                team.getDisplayName(),
                team.getDescription(),
                team.getOwnerEmail()
        );

        return new TeamDTO(
                teamInfoDTO,
                team.getMembers().stream()
                        .map(member -> memberMapper.mapToMemberDTO(member))
                        .collect(Collectors.toList()),
                team.getBoard().stream()
                        .map(board -> boardMapper.mapToBoardDTO(board))
                        .collect(Collectors.toList())
        );
    }

    public List<TeamDTO> mapToTeamDTOList(List<Team> teams) {
        return teams.stream()
                .map(this::mapToTeamDTO)
                .collect(Collectors.toList());
    }
}
