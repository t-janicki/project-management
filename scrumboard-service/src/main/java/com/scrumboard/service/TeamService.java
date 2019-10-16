package com.scrumboard.service;

import com.scrumboard.domain.Team;

import java.util.List;

public interface TeamService {

    Team createNewTeam(String displayName, String description);

    Team getTeamById(Long teamId);

    List<Team> getTeams();
}
