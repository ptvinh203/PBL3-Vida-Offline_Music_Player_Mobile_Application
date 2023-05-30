package com.pbl3.musicapplication.model.model;

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
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;

    @Override
    public String toString() {
        return "RegisterRequest [username=" + username + ", password=" + password + ", fullName=" + fullName
                + ", phoneNumber=" + phoneNumber + "]";
    }
}
