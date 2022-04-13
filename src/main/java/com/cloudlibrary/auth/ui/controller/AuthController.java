package com.cloudlibrary.auth.ui.controller;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import com.cloudlibrary.auth.ui.requestBody.*;
import com.cloudlibrary.auth.ui.view.ApiResponseView;
import com.cloudlibrary.auth.ui.view.auth.AuthCompactView;
import com.cloudlibrary.auth.ui.view.auth.AuthView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.Plugin;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "회원 API")
@RequestMapping("/v1/auth")
public class AuthController {

    /*
    private final AdminOperationUseCase adminOperationUseCase;
    private final AdminReadUseCase adminReadUseCase;

    @Autowired
    public AdminController(AdminOperationUseCase adminOperationUseCase, AdminReadUseCase adminReadUseCase) {
        this.adminOperationUseCase = adminOperationUseCase;
        this.adminReadUseCase = adminReadUseCase;
    }
     */

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseView<AuthView>> createAuth(@RequestBody AuthCreateRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }
/*
        var command = AdminOperationUseCase.AdminCreatedCommand.builder()
                .rid(request.getRid())
                .libraryId(request.getLibraryId())
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .thumbnailImage(request.getThumbnailImage())
                .coverImage(request.getCoverImage())
                .build();

        var result = AdminOperationUseCase.createAdmin(command);
*/
        //return ResponseEntity.ok(new ApiResponseView<>(new AdminView(result)));

        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder()
                .uid(1L)
                .userId("kim123")
                .password("123123")
                .userName("김김김")
                .gender("남")
                .birth(LocalDateTime.now())
                .address("서울시 서초구 방배동")
                .email("gj@nmm.jy")
                .tell("010-55-55")
                .sendAgree(true)
                .build()
        ));
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<ApiResponseView<AuthView>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().build();
    }

    //마이페이지조회
    @GetMapping("/{uid}")
    public ResponseEntity<ApiResponseView<AuthView>> getAuth(@PathVariable("uid") Long uid) {
        //var query = new AdminReadUseCase.AdminFindQuery(id);

        //var result = AdminReadUseCase.getAdmin(query);

        //return ResponseEntity.ok(new ApiResponseView<>(new AdminView(result)));
        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder()
                .uid(1L)
                .userId("kim123")
                .password("123123")
                .userName("김김김")
                .gender("남")
                .birth(LocalDateTime.now())
                .address("서울시 서초구 방배동")
                .email("gj@nmm.jy")
                .tell("010-55-55")
                .sendAgree(true)
                .build()
        ));
    }

    //회원수정
    @PatchMapping("/update-state/{uid}")
    public ResponseEntity<ApiResponseView<AuthCompactView>> updateAuth(@RequestBody AuthUpdateRequest request, @PathVariable("uid") Long uid) {

        return ResponseEntity.ok().build();
    }

    //회원탈퇴
    @DeleteMapping("/withdraw/{uid}")
    public ResponseEntity<ApiResponseView<AuthCompactView>> deleteAuth(@PathVariable("uid") Long uid) {

        return ResponseEntity.ok().build();

    }

    //회원아이디찾기
    @PostMapping("/findid/{uid}")
    public ResponseEntity<ApiResponseView<AuthView>> findId(@RequestBody AuthFindIdRequest request, @PathVariable("uid") Long uid) {
        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder().userId("myid").build()));
    }

    //회원비밀번호찾기
    @PatchMapping("/findpw/{uid}")
    public ResponseEntity<ApiResponseView<AuthView>> findPw(@RequestBody AuthFindPwRequest request, @PathVariable("uid") Long uid) {
        return ResponseEntity.ok(new ApiResponseView<>(AuthView.builder().password("mypw").build()));
    }
}
