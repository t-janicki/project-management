package com.scrumboard.service;

import com.scrumboard.domain.Member;

public interface MemberService {

    Member getMemberByEmail(String userName, String avatarUrl, String email);
}
