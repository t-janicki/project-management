package com.scrumboard.service;

import com.scrumboard.domain.Member;

public interface MemberService {

    Member registerMember(String name, String avatarUrl, Long userId);

    Member updateMember(String name, String avatarUrl, Long userId);
}
