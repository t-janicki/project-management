package com.account.service.impl;

import com.account.domain.AuthProvider;
import com.account.domain.Role;
import com.account.domain.RoleName;
import com.account.domain.User;
import com.account.repository.RoleRepository;
import com.account.repository.UserRepository;
import com.account.service.LayoutSettingsService;
import com.account.service.UserService;
import com.utility.exception.BadRequestException;
import com.utility.exception.NotFoundException;
import com.utility.exception.ResourceNotFoundException;
import com.utility.web.request.user.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

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
        user.setSettings(settingsService.createDefaultLayoutSettings());

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
