package com.cloudlibrary.auth.ui.view.auth;

import com.cloudlibrary.auth.application.service.AuthReadUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthView {
    @ApiModelProperty(value = "uid")
    private final Long uid;
    @ApiModelProperty(value = "user가 입력하는 id")
    private final String userId;
    @ApiModelProperty(value = "성함")
    private final String userName;
    @ApiModelProperty(value = "성별")
    private final String gender;
    @ApiModelProperty(value = "생년월일")
    private final String birth;
    @ApiModelProperty(value = "주소")
    private final String address;
    @ApiModelProperty(value = "이메일")
    private final String email;
    @ApiModelProperty(value = "전화번호")
    private final String tel;

    public AuthView(AuthReadUseCase.FindAuthResult result) {
        this.uid = result.getUid();
        this.userId = result.getUserId();
        this.userName = result.getUserName();
        this.gender = result.getGender();
        this.birth = result.getBirth();
        this.address = result.getAddress();
        this.email = result.getEmail();
        this.tel = result.getTel();
    }

}
