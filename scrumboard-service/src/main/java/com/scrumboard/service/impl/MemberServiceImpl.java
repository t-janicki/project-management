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
    public Member registerMember(String name, String avatarUrl, Long userId) {
        Member member = new Member();
        member.setName(name);
        member.setAvatarUrl(avatarUrl);
        member.setUserId(userId);

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(String name, String avatarUrl, Long userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Member not found"));

        member.setName(name);
        member.setAvatarUrl(avatarUrl);

        return memberRepository.save(member);
    }
}
