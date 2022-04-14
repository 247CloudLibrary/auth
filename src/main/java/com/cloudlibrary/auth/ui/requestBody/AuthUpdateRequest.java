package com.cloudlibrary.auth.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthUpdateRequest {
    private Long uid;
    private String userId;
    private String password;
    private String userName;
    private String gender;
    private String birth;
    private String address;
    private String email;
    private String tel;
}
