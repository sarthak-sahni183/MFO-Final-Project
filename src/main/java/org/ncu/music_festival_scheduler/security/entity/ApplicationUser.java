package org.ncu.music_festival_scheduler.security.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String emailAddress;

    private String password;

    private String role; // ROLE_ORGANIZER or ROLE_VIEWER

    public ApplicationUser() {}

    public ApplicationUser(Long userId, String emailAddress, String password, String role) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }

    public ApplicationUser(String emailAddress, String password, String role) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
