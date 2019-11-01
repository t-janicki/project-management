package com.scrumboard.service;

import com.scrumboard.domain.Team;

import java.util.List;

public interface TeamService {

    Team getTeamById(Long teamId);

    Team createNewTeam(String displayName, String description, String userName, String avatarUrl, String email);

    Team getTeamByIdAndMembersIn(Long teamId, String name, String avatarUrl, String email);

    List<Team> getTeamsByMembersIn(String name, String avatarUrl, String email);

    Team updateTeamInfo(Long id, String displayName, String description, String email);

    Team inviteMemberToTeam(Long teamId, String email);

    Team removeMemberFromTeam(Long teamId, String email);

}
