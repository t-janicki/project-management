package com.account.service.impl;

import com.account.domain.AuthProvider;
import com.account.domain.Role;
import com.account.domain.RoleName;
import com.account.domain.User;
import com.account.repository.RoleRepository;
import com.account.repository.UserRepository;
import com.account.service.LayoutSettingsService;
import com.account.service.UserService;
import com.utility.dto.user.UserDTO;
import com.utility.exception.BadRequestException;
import com.utility.exception.NotFoundException;
import com.utility.exception.ResourceNotFoundException;
import com.utility.exception.user.UserMessages;
import com.utility.web.request.user.NewPasswordRequest;
import com.utility.web.request.user.SignUpRequest;
import com.utility.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.utility.validation.PhoneValidation.validatePhoneNumberFormat;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private LayoutSettingsService settingsService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           LayoutSettingsService settingsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.settingsService = settingsService;
    }

    @Override
    public User registerUser(SignUpRequest request) {
        this.checkEmailAvailability(request.getEmail());

        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Role not found"));

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProvider(AuthProvider.local);
        user.setActive(Boolean.TRUE);
        user.setDeleted(Boolean.FALSE);
        user.setEmailVerificationToken(null);
        user.setRoles(Collections.singleton(role));
        user.setAvatarUrl("assets/images/avatars/profile.jpg");
        user.setSettings(settingsService.createDefaultLayoutSettings());

        try {
            user = userRepository.save(user);
            return user;

        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Email already in use. ");
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User getDummyUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(new User("", "assets/images/avatars/unknown.jpg"));
    }

    public ApiResponse newPasswordRequest(Long id, NewPasswordRequest request) {
        User user = getUserById(id);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new BadRequestException(UserMessages.ACCESS_DENIED.getMessage());

        } else if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {

            throw new BadRequestException(UserMessages.DIFFERENT_PASSWORD.getMessage());

        } else if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {

            throw new BadRequestException(UserMessages.PASSWORDS_EQUALS.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new ApiResponse(true, UserMessages.PASSWORD_CHANGED.getMessage());
    }

    public User updateUser(Long id, UserDTO request) {
        User user = getUserById(id);

        if (!user.getEmail().equals(request.getEmail())) {
            this.checkEmailAvailability(request.getEmail());
        }

        if (!request.getPhone().isEmpty()) {
            validatePhoneNumberFormat(request.getPhone());
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setJobTitle(request.getJobTitle());
        user.setAvatarUrl(request.getAvatarUrl());

        userRepository.save(user);

        return user;
    }

    private void checkEmailAvailability(String email) {
        if (userRepository.existsByEmailAndIsDeletedIsFalse(email)) {
            throw new BadRequestException("Email already in use.");
        }
    }
}
