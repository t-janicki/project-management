package com.scrumboard.service.impl;

import com.scrumboard.domain.Team;
import com.scrumboard.repository.TeamRepository;
import com.scrumboard.service.TeamService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createNewTeam(String displayName, String description) {
        Team team = new Team();

        team.setDisplayName(displayName);
        team.setDescription(description);
        team.setOwnerId(1L);
        team.setMembers(new ArrayList<>());
        team.setBoard(new ArrayList<>());

        return teamRepository.save(team);
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
