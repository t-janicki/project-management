package com.scrumboard.service.impl;

import com.scrumboard.domain.Member;
import com.scrumboard.domain.Team;
import com.scrumboard.repository.MemberRepository;
import com.scrumboard.repository.TeamRepository;
import com.scrumboard.service.MemberService;
import com.scrumboard.service.TeamService;
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
    private MemberRepository memberRepository;
    private MemberService memberService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,
                           MemberRepository memberRepository,
                           MemberService memberService) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public Team createNewTeam(String displayName, String description,
                              String userName, String avatarUrl, String email) {

        Member member = getMemberByUserId(userName, avatarUrl, email);

        Team team = new Team();

        team.setDisplayName(displayName);
        team.setDescription(description);
        team.setOwnerEmail(email);
        team.setMembers(Collections.singletonList(member));
        team.setBoard(new ArrayList<>());

        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByIdAndMembersIn(Long teamId, String userName, String avatarUrl, String email) {
        Member member = getMemberByUserId(userName, avatarUrl, email);

        return teamRepository.findByIdAndMembersIn(teamId, Collections.singletonList(member))
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public List<Team> getTeamsByMembersIn(String userName, String avatarUrl, String email) {
        Member member = getMemberByUserId(userName, avatarUrl, email);

        return teamRepository.findAllByMembersIn(Collections.singletonList(member));
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

    private Member getMemberByUserId(String userName, String avatarUrl, String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member member;
        if (optionalMember.isPresent()) {

            member = memberService.updateMember(userName, avatarUrl, email);

        } else {

            member = memberService.registerMember(userName, avatarUrl, email);
        }

        return member;
    }

}
