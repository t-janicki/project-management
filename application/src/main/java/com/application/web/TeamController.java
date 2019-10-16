package com.application.web;

import com.application.mapper.scrumboard.TeamMapper;
import com.scrumboard.domain.Team;
import com.scrumboard.service.TeamService;
import com.utility.dto.scrumboard.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;
    private TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService,
                          TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public TeamDTO createNewTeam(@RequestBody TeamDTO teamDTO) {

//        System.out.println(teamDTO.getDescription().toString());
//        System.out.println(teamDTO.getDisplayName().toString());
        Team team = teamService.createNewTeam(teamDTO.getDisplayName(), teamDTO.getDescription());

        return teamMapper.mapToTeamDTO(team);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<TeamDTO> getTeams() {
        return teamMapper.mapToTeamDTOList(teamService.getTeams());
    }

    @GetMapping(value = "/{teamId}", produces = APPLICATION_JSON_VALUE)
    public TeamDTO getTeamById(@PathVariable Long teamId) {
        return teamMapper.mapToTeamDTO(teamService.getTeamById(teamId));
    }
}
