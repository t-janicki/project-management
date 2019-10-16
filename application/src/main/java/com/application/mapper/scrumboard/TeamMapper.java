package com.application.mapper.scrumboard;

import com.scrumboard.domain.Team;
import com.utility.dto.scrumboard.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TeamMapper {
    private MemberMapper memberMapper;

    @Autowired
    public TeamMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public TeamDTO mapToTeamDTO(Team team) {
        return new TeamDTO(
                team.getId(),
                team.getDisplayName(),
                team.getDescription(),
                team.getOwnerId(),
                team.getMembers().stream()
                        .map(v -> memberMapper.mapToMemberDTO(v))
                        .collect(Collectors.toList())
        );
    }

    public List<TeamDTO> mapToTeamDTOList(List<Team> teams) {
        return teams.stream()
                .map(this::mapToTeamDTO)
                .collect(Collectors.toList());
    }
}
