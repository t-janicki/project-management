package com.scrumboard.service;

import com.scrumboard.domain.Team;

import java.util.List;

public interface TeamService {

    Team getTeamById(Long teamId);

    Team createNewTeam(String displayName, String description, String userName, String avatarUrl, Long userId);

    Team getTeamByIdAndMembersIn(Long teamId, String name, String avatarUrl, Long userId);

    List<Team> getTeamsByMembersIn(String name, String avatarUrl, Long userId);

    Team updateTeamInfo(Long id, String displayName, String description, Long ownerId);
}
