package com.cloudlibrary.auth.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthCreateRequest {

    @NotBlank(message = "아이디는 필수값입니다")
    private String userId;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}"
            , message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수값입니다")
    private String password;

    @NotBlank(message = "이름은 필수값입니다")
    private String userName;

    @NotBlank(message = "성별은 필수값입니다")
    private String gender;

    @NotBlank(message = "생년월일은 필수값입니다")
    private String birth;

    @NotBlank(message = "주소는 필수값입니다")
    private String address;

    @Email(message = "올바른 이메일 주소를 입력해주세요")
    @NotBlank(message = "이메일은 필수값입니다")
    private String email;

    @NotBlank(message = "전화번호는 필수값입니다")
    private String tel;
}