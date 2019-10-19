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

    public Team createNewTeam(String displayName, String description,
                              String name, String avatarUrl, Long userId) {

        Member member = getMemberByUserId(name, avatarUrl, userId);

        Team team = new Team();

        team.setDisplayName(displayName);
        team.setDescription(description);
        team.setOwnerId(1L);
        team.setMembers(Collections.singletonList(member));
        team.setBoard(new ArrayList<>());

        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByIdAndMembersIn(Long teamId, String name, String avatarUrl, Long userId) {
        Member member = getMemberByUserId(name, avatarUrl, userId);

        return teamRepository.findByIdAndMembersIn(teamId, Collections.singletonList(member))
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public List<Team> getTeamsByMembersIn(String name, String avatarUrl, Long userId) {
        Member member = getMemberByUserId(name, avatarUrl, userId);

        return teamRepository.findAllByMembersIn(Collections.singletonList(member));
    }

    private Member getMemberByUserId(String name, String avatarUrl, Long userId) {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);

        Member member;
        if (optionalMember.isPresent()) {

            member = memberService.updateMember(name, avatarUrl, userId);

        } else {

            member = memberService.registerMember(name, avatarUrl, userId);
        }

        return member;
    }


}
