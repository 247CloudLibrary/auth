package com.cloudlibrary.auth.ui.controller;

import com.cloudlibrary.auth.application.service.AuthOperationUseCase;
import com.cloudlibrary.auth.application.service.AuthReadUseCase;
import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import com.cloudlibrary.auth.ui.requestBody.*;
import com.cloudlibrary.auth.ui.view.ApiResponseView;
import com.cloudlibrary.auth.ui.view.auth.AuthView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "회원 API")
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthOperationUseCase authOperationUseCase;
    private final AuthReadUseCase authReadUseCase;

    public AuthController(AuthOperationUseCase authOperationUseCase, AuthReadUseCase authReadUseCase) {
        this.authOperationUseCase = authOperationUseCase;
        this.authReadUseCase = authReadUseCase;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("health-check");}

    @PostMapping("/signup")
    @ApiOperation("회원가입")
    public ResponseEntity<Void> createAuth(@Valid @RequestBody AuthCreateRequest request) {

        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        var command = AuthOperationUseCase.AuthCreateCommand.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .userName(request.getUserName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .build();

        authOperationUseCase.createAuth(command);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uid}")
    @ApiOperation("마이페이지 조회")
    public ResponseEntity<ApiResponseView<AuthView>> getAuth(@PathVariable("uid") Long uid) {

        var query = new AuthReadUseCase.AuthFindQuery(uid);

        var result = authReadUseCase.getAuthInfo(query);

        return ResponseEntity.ok(new ApiResponseView<>(new AuthView(result)));
    }


    @PatchMapping("/update-state/{uid}")
    @ApiOperation("회원수정")
    public ResponseEntity<Void> updateAuth(@PathVariable("uid") Long uid, @Valid @RequestBody AuthUpdateRequest request) {

        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        var command = AuthOperationUseCase.AuthUpdateCommand.builder()
                .uid(uid)
                .userId(request.getUserId())
                .userName(request.getUserName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .build();

        authOperationUseCase.updateAuth(command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-pw/{uid}")
    @ApiOperation("비밀번호 수정")
    public ResponseEntity<Void> updatePassword(@PathVariable("uid") Long uid, @Valid @RequestBody AuthUpdatePassword request) {

        var command = AuthOperationUseCase.AuthUpdatePasswordCommand.builder()
                .uid(uid)
                .oldPassword(request.getOldPassword())
                .newPassword(request.getNewPassword())
                .build();

        authOperationUseCase.updatePassword(command);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/withdraw/{uid}")
    @ApiOperation("회원 탈퇴")
    public ResponseEntity<Void> deleteAuth(@PathVariable("uid") Long uid) {

        var command =
                AuthOperationUseCase.AuthDeleteCommand.builder().uid(uid).build();

        authOperationUseCase.deleteAuth(command);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/findid")
    @ApiOperation("아이디 찾기")
    public ResponseEntity<ApiResponseView<AuthView>> findId(@Valid @RequestBody AuthFindIdRequest request) {

        var command = AuthOperationUseCase.AuthFindIdCommand.builder().email(request.getEmail()).build();

        var result = authOperationUseCase.findAuthId(command);

        return ResponseEntity.ok(new ApiResponseView<>(new AuthView(result)));
    }

    @PatchMapping("/findpw")
    @ApiOperation("비밀번호 찾기")
    public ResponseEntity<ApiResponseView<String>> findPw(@RequestBody AuthFindPwRequest request) {
       var command = AuthOperationUseCase.AuthFindPWCommand.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .build();

        String result = authOperationUseCase.findAuthPW(command);

        return ResponseEntity.ok(new ApiResponseView<>(result));
    }
}
