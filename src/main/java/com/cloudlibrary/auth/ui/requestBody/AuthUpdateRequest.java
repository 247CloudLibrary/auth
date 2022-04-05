package com.cloudlibrary.auth.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthUpdateRequest {
    private Long uid;
    private long userId;
    private String password;
    private String userName;
    private String gender;
    private Date birth;
    private String address;
    private String email;
    private String tell;
    private boolean sendAgree;
}
