package com.cloudlibrary.auth.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthFindIdRequest {

    @Email(message = "올바른 이메일 주소를 입력해주세요")
    @NotBlank(message = "이메일은 필수값입니다")
    private String email;
}
