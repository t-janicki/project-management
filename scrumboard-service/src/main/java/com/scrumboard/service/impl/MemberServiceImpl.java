package com.scrumboard.service.impl;

import com.scrumboard.domain.Member;
import com.scrumboard.repository.MemberRepository;
import com.scrumboard.service.MemberService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member registerMember(String name, String avatarUrl, String email) {
        Member member = new Member();
        member.setName(name);
        member.setAvatarUrl(avatarUrl);
        member.setEmail(email);

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(String name, String avatarUrl, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        member.setName(name);
        member.setAvatarUrl(avatarUrl);

        return memberRepository.save(member);
    }
}
