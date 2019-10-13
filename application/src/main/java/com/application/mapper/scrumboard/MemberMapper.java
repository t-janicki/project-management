package com.application.mapper.scrumboard;

import com.scrumboard.domain.Member;
import com.utility.dto.scrumboard.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public final class MemberMapper {

    public Member mapToMember(MemberDTO memberDTO) {
        return new Member(
                memberDTO.getId(),
                memberDTO.getName(),
                memberDTO.getAvatarUrl(),
                memberDTO.getUserId()
        );
    }

    public MemberDTO mapToMemberDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getAvatarUrl(),
                member.getUserId()
        );
    }
}
