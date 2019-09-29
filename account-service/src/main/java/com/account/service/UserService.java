package com.account.service;

import com.account.domain.User;
import com.utility.web.request.user.SignUpRequest;

public interface UserService {

    User registerUser(SignUpRequest request);

    User getCurrentUser(Long id);
}
