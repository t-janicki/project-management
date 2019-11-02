package com.scrumboard.service.impl;

import com.scrumboard.domain.Member;
import com.scrumboard.domain.Team;
import com.scrumboard.repository.TeamRepository;
import com.scrumboard.service.MemberService;
import com.scrumboard.service.TeamService;
import com.utility.exception.AccessForbiddenException;
import com.utility.exception.BadRequestException;
import com.utility.exception.ExistsException;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private MemberService memberService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,
                           MemberService memberService) {
        this.teamRepository = teamRepository;
        this.memberService = memberService;
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public Team createNewTeam(String displayName, String description,
                              String userName, String avatarUrl, String email) {

        Member member = memberService.getMemberByEmail(userName, avatarUrl, email);

        Team team = new Team();

        team.setDisplayName(displayName);
        team.setDescription(description);
        team.setOwnerEmail(email);
        team.setDeleted(Boolean.FALSE);
        team.setMembers(Collections.singletonList(member));
        team.setBoard(new ArrayList<>());

        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByIdAndMembersInAndIsDeletedIsFalse(Long teamId, String userName, String avatarUrl, String email) {
        Member member = memberService.getMemberByEmail(userName, avatarUrl, email);

        return teamRepository.findByIdAndMembersInAndIsDeletedIsFalse(teamId, Collections.singletonList(member))
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public List<Team> getTeamsByMembersInAndIsDeletedIsFalse(String userName, String avatarUrl, String email) {
        Member member = memberService.getMemberByEmail(userName, avatarUrl, email);

        return teamRepository.findAllByMembersInAndIsDeletedIsFalse(Collections.singletonList(member));
    }

    @Override
    public Team updateTeamInfo(Long id, String displayName, String description, String email) {
        Team team = getTeamById(id);

        team.setDisplayName(displayName);
        team.setDescription(description);
        team.setOwnerEmail(email);

        teamRepository.save(team);

        return team;
    }

    @Override
    public Team inviteMemberToTeam(Long teamId, String email, String userName, String avatarUrl) {
        Team team = getTeamById(teamId);

        Optional<Member> memberOptional = team.getMembers().stream()
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (memberOptional.isPresent()) {
            throw new ExistsException("Member already added. ");
        }

        Member member = memberService.getMemberByEmail(userName, avatarUrl, email);

        team.getMembers().add(member);

        return teamRepository.save(team);
    }

    @Override
    public Team removeMemberFromTeam(Long teamId, String email, String currentUserEmail) {
        Team team = getTeamById(teamId);

        Optional<Member> memberOptional = team.getMembers().stream()
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (!memberOptional.isPresent()) {
            throw new NotFoundException("Member not found. ");

        } else if(email.equalsIgnoreCase(team.getOwnerEmail())) {
            throw new BadRequestException("Cannot remove leader. ");

        } else if (email.equalsIgnoreCase(currentUserEmail) || currentUserEmail.equalsIgnoreCase(team.getOwnerEmail())) {
            team.getMembers().remove(memberOptional.get());

            return teamRepository.save(team);

        } else {
            throw new AccessForbiddenException("Forbidden. ");
        }
    }

    @Override
    public Team deleteTeam(Long teamId, String currentUserEmail) {
        Team team = getTeamById(teamId);

        if (team.getOwnerEmail().equalsIgnoreCase(currentUserEmail)) {

            team.setDeleted(Boolean.TRUE);
            teamRepository.save(team);

            return team;
        } else {
            throw new AccessForbiddenException("Forbidden. ");
        }
    }

}
