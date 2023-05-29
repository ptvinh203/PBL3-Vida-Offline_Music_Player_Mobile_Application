package com.pbl3.musicapplication.model.model;

import com.pbl3.musicapplication.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserModel {
    private Integer userId;
    private String username;
    private boolean authentication;

    public UserModel(User entity) {
        this.userId = entity.getUserId();
        this.username = entity.getUsername();
    }

    @Override
    public String toString() {
        return "UserModel [userId=" + userId + ", username=" + username + ", authentication=" + authentication + "]";
    }

}
