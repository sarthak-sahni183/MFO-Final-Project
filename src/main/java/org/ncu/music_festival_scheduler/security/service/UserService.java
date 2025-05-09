package org.ncu.music_festival_scheduler.security.service;

import org.ncu.music_festival_scheduler.security.entity.ApplicationUser;
import org.ncu.music_festival_scheduler.security.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public ApplicationUser registerUser(String emailAddress, String password, String role) {
        String hashed = encoder.encode(password);
        ApplicationUser user = new ApplicationUser(emailAddress, hashed, "ROLE_" + role.toUpperCase());
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        ApplicationUser user = userRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getEmailAddress(), user.getPassword(),
                Collections.singleton(() -> user.getRole()));
    }
}
