package com.scrumboard.service;

import com.scrumboard.domain.Member;

public interface MemberService {

    Member getMemberByEmail(String name, String avatarUrl, String email);
}
