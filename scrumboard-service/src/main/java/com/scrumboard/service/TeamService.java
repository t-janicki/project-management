package com.scrumboard.service;

import com.scrumboard.domain.Team;

import java.util.List;

public interface TeamService {

    Team getTeamById(Long teamId);

    Team createNewTeam(String displayName, String description, String userName, String avatarUrl, String email);

    Team getTeamByIdAndMembersInAndIsDeletedIsFalse(Long teamId, String name, String avatarUrl, String email);

    List<Team> getTeamsByMembersInAndIsDeletedIsFalse(String name, String avatarUrl, String email);

    Team updateTeamInfo(Long id, String displayName, String description, String email);

    Team inviteMemberToTeam(Long teamId, String email, String userName, String avatarUrl);

    Team removeMemberFromTeam(Long teamId, String email, String currentUserEmail);

    Team deleteTeam(Long teamId, String currentUserEmail);
}
