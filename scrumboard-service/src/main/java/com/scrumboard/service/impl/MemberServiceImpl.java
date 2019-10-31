package com.scrumboard.service.impl;

import com.scrumboard.domain.Member;
import com.scrumboard.repository.MemberRepository;
import com.scrumboard.service.MemberService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private Member registerMember(String name, String avatarUrl, String email) {
        Member member = new Member();
        member.setName(name);
        member.setAvatarUrl(avatarUrl);
        member.setEmail(email);

        return memberRepository.save(member);
    }

    private Member updateMember(String name, String avatarUrl, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        member.setName(name);
        member.setAvatarUrl(avatarUrl);

        return memberRepository.save(member);
    }

    public Member getMemberByEmail(String userName, String avatarUrl, String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member member;
        if (optionalMember.isPresent()) {

            member = updateMember(userName, avatarUrl, email);

        } else {

            member = registerMember(userName, avatarUrl, email);
        }

        return member;
    }
}
