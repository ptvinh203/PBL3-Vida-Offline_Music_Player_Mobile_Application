package com.pbl3.musicapplication.model.entity;

import com.pbl3.musicapplication.model.model.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer userId;

    // @Column(name = "USERNAME", unique = true)
    private String username;
    private String password;

    public User(UserModel userModel) {
        this.username = userModel.getUsername();
    }

    public boolean isValid() {
        return (username != null && !username.isEmpty());
    }
}
