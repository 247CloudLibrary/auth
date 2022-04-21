package com.cloudlibrary.auth.infrastructure.persistence.mysql.entity;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.application.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tel;


    public Auth toAuth() {
        return Auth.builder()
                .uid(this.uid)
                .userId(this.userId)
                .password(this.password)
                .userName(this.userName)
                .gender(this.gender.getType())
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
        this.gender = Gender.find(auth.getGender());
        this.birth = auth.getBirth();
        this.address = auth.getAddress();
        this.email = auth.getEmail();
        this.tel = auth.getTel();
    }
}
