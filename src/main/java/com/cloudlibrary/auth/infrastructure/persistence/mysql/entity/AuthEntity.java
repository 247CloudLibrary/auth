package com.cloudlibrary.auth.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.auth.application.domain.Auth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "auth")
public class AuthEntity extends BaseTimeEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
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
                .createdAt(super.getCreatedAt())
                .updatedAt(super.getUpdatedAt())
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
