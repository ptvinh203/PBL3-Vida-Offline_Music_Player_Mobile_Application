package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserModel {
    private Integer userId;
    private String username;
    private String fullName;
    private String phoneNumber;
}
