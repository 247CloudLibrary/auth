package com.cloudlibrary.auth.ui.controller;

import com.cloudlibrary.auth.application.service.AuthOperationUseCase;
import com.cloudlibrary.auth.application.service.AuthReadUseCase;
import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import com.cloudlibrary.auth.ui.requestBody.*;
import com.cloudlibrary.auth.ui.view.ApiResponseView;
import com.cloudlibrary.auth.ui.view.auth.AuthCompactView;
import com.cloudlibrary.auth.ui.view.auth.AuthView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signUp")
    @ApiOperation("회원가입")
    public void createAuth(@Valid @RequestBody AuthCreateRequest request) {

        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
        //TODO 회원 중복 체크

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

        authOperationUseCase.createdAuth(command);
    }

    @PostMapping("/signin")
    @ApiOperation("로그인")
    public ResponseEntity<ApiResponseView<AuthView>> login(@RequestBody LoginRequest request) {
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
    public ResponseEntity<ApiResponseView<AuthCompactView>> updateAuth(@RequestBody AuthUpdateRequest request, @PathVariable("uid") Long uid) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/withdraw/{uid}")
    @ApiOperation("회원 탈퇴")
    public ResponseEntity<ApiResponseView<AuthCompactView>> deleteAuth(@PathVariable("uid") Long uid) {

        return ResponseEntity.ok().build();

    }

    @PostMapping("/findid/{uid}")
    @ApiOperation("아이디 찾기")
    public ResponseEntity<ApiResponseView<AuthView>> findId(@RequestBody AuthFindIdRequest request, @PathVariable("uid") Long uid) {
        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder().userId("myid").build()));
    }

    @PatchMapping("/findpw/{uid}")
    @ApiOperation("비밀번호 찾기")
    public ResponseEntity<ApiResponseView<AuthView>> findPw(@RequestBody AuthFindPwRequest request, @PathVariable("uid") Long uid) {
        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder().password("mypw").build()));
    }
}
