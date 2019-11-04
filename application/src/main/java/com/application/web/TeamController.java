package com.application.web;

import com.account.domain.User;
import com.account.service.UserService;
import com.application.mapper.scrumboard.TeamMapper;
import com.auth.security.CurrentUser;
import com.auth.security.UserPrincipal;
import com.email.InviteInfo;
import com.email.Mail;
import com.email.service.SimpleEmailService;
import com.scrumboard.domain.Team;
import com.scrumboard.service.TeamService;
import com.scrumboard.web.InviteRequest;
import com.utility.dto.scrumboard.MemberDTO;
import com.utility.dto.scrumboard.TeamDTO;
import com.utility.dto.scrumboard.TeamInfoDTO;
import com.utility.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;
    private TeamMapper teamMapper;
    private UserService userService;
    private SimpleEmailService simpleEmailService;


    @Autowired
    public TeamController(TeamService teamService,
                          TeamMapper teamMapper,
                          UserService userService,
                          SimpleEmailService simpleEmailService) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.userService = userService;
        this.simpleEmailService = simpleEmailService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public TeamDTO createNewTeam(@CurrentUser UserPrincipal userPrincipal,
                                 @RequestBody TeamDTO teamDTO) {
        User user = userService.getUserById(userPrincipal.getId());

        Team team = teamService.createNewTeam(
                teamDTO.getTeamInfo().getDisplayName(),
                teamDTO.getTeamInfo().getDescription(),
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getEmail()
        );

        return teamMapper.mapToTeamDTO(team);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<TeamDTO> getTeams(@CurrentUser UserPrincipal userPrincipal) {
        User user = userService.getUserById(userPrincipal.getId());

        List<Team> teams = teamService.getTeamsByMembersInAndIsDeletedIsFalse(
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getEmail()
        );

        return teamMapper.mapToTeamDTOList(teams);
    }

    @GetMapping(value = "/{teamId}", produces = APPLICATION_JSON_VALUE)
    public TeamDTO getTeamById(@CurrentUser UserPrincipal userPrincipal,
                               @PathVariable Long teamId) {
        User user = userService.getUserById(userPrincipal.getId());

        Team team = teamService.getTeamByIdAndMembersInAndIsDeletedIsFalse(
                teamId,
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getEmail()
        );

        return teamMapper.mapToTeamDTO(team);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public TeamDTO updateTeamInfo(@RequestBody TeamInfoDTO teamInfoDTO) {

        Team team = teamService.updateTeamInfo(
                teamInfoDTO.getId(),
                teamInfoDTO.getDisplayName(),
                teamInfoDTO.getDescription(),
                teamInfoDTO.getOwnerEmail()
        );

        return teamMapper.mapToTeamDTO(team);
    }

    @PutMapping(value = "/invite", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public List<MemberDTO> inviteMemberToTeam(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody InviteRequest request,
                                              @RequestHeader Map<String, String> headers) {
        User user = userService.getDummyUserByEmail(request.getEmail());

        Team team = teamService.inviteMemberToTeam(request.getTeamId(), request.getEmail(), user.getDisplayName(), user.getAvatarUrl());

        String teamUrl = headers.get("referer");

        simpleEmailService.sendInvitationMessage(
                new Mail(
                request.getEmail(),
                "Team Invitation"
                ),
                new InviteInfo(
                        userPrincipal.getEmail(),
                        team.getDisplayName(),
                        teamUrl
                ));

        return teamMapper.mapToTeamDTO(team).getMembers();
    }

    @PatchMapping(value = "/remove", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public TeamDTO removeMemberFromTeam(@CurrentUser UserPrincipal userPrincipal,
                                        @RequestBody InviteRequest request) {
        String currentUserEmail = userService.getUserById(userPrincipal.getId()).getEmail();

        Team team = teamService.removeMemberFromTeam(request.getTeamId(), request.getEmail(), currentUserEmail);

        return teamMapper.mapToTeamDTO(team);
    }

    @DeleteMapping(value = "/{teamId}")
    public ApiResponse deleteTeam(@CurrentUser UserPrincipal userPrincipal,
                                  @PathVariable Long teamId) {

        teamService.deleteTeam(teamId, userPrincipal.getEmail());

        return new ApiResponse(true, "Team deleted. ");
    }
}
