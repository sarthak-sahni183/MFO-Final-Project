package org.ncu.music_festival_scheduler.security.repository;

import org.ncu.music_festival_scheduler.security.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmailAddress(String emailAddress);
}
