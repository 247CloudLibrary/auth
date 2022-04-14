package com.cloudlibrary.auth.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.auth.application.domain.Auth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AuthEntity implements Serializable {
    private Long uid;
    private String userId;
    private String password;
    private String userName;
    private String gender;
    private String birth;
    private String address;
    private String email;
    private String tel;
    private boolean sendAgree;

    public Auth toAuth() {
        return Auth.builder()
                .uid(this.uid)
                .userId(this.userId)
                .password(this.password)
                .userName(this.userName)
                .gender(this.gender)
                .birth(this.birth)
                .address(this.address)
                .email(this.email)
                .tel(this.tel)
                .build();
    }

    public AuthEntity(Auth auth) {
        this.uid = auth.getUid();
        this.userId = auth.getUserId();
        this.password = auth.getPassword();
        this.userName = auth.getUserName();
        this.gender = auth.getGender();
        this.birth = auth.getBirth();
        this.address = auth.getAddress();
        this.email = auth.getEmail();
        this.tel = auth.getTel();
    }
}
