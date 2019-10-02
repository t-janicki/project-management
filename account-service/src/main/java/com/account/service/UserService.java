package com.account.service;

import com.account.domain.User;
import com.utility.dto.user.UserDTO;
import com.utility.web.request.user.NewPasswordRequest;
import com.utility.web.request.user.SignUpRequest;
import com.utility.web.response.ApiResponse;

public interface UserService {

    User registerUser(SignUpRequest request);

    User getUserById(Long id);

    ApiResponse newPasswordRequest(Long id, NewPasswordRequest request);

    User updateUser(Long id, UserDTO userDTO);
}
