package com.lecture.car_rental1.service;

import com.lecture.car_rental1.domain.Role;
import com.lecture.car_rental1.domain.User;
import com.lecture.car_rental1.domain.enumeration.UserRole;
import com.lecture.car_rental1.exception.AuthException;
import com.lecture.car_rental1.exception.BadRequestException;
import com.lecture.car_rental1.exception.ConflictException;
import com.lecture.car_rental1.exception.ResourceNotFoundException;
import com.lecture.car_rental1.repository.RoleRepository;
import com.lecture.car_rental1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(User user) throws BadRequestException {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Error: Email is already in use!");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setBuiltIn(false);

        Set<Role> roles = new HashSet<>();
        Role customerRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(customerRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    public void login(String email, String password) throws AuthException {
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if (!BCrypt.checkpw(password, user.get().getPassword()))
                throw new AuthException("invalid credentials");
        } catch (Exception e) {
            throw new AuthException("invalid credentials");
        }
    }


}
