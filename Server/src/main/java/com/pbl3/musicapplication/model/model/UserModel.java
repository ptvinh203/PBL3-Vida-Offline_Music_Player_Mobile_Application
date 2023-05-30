package com.pbl3.musicapplication.model.model;

import com.pbl3.musicapplication.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserModel {
    private Integer userId;
    private String username;
    private boolean authentication;
    private String fullName;
    private String phoneNumber;

    public UserModel(User entity) {
        this.userId = entity.getUserId();
        this.username = entity.getUsername();
        this.fullName = entity.getFullName();
        this.phoneNumber = entity.getPhoneNumber();
    }

}
