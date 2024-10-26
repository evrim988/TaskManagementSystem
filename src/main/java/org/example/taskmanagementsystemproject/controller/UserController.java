package org.example.taskmanagementsystemproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskmanagementsystemproject.dto.request.user.LoginRequestDto;
import org.example.taskmanagementsystemproject.dto.request.user.RegisterRequestDto;
import org.example.taskmanagementsystemproject.dto.response.BaseResponse;
import org.example.taskmanagementsystemproject.entity.User;
import org.example.taskmanagementsystemproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<BaseResponse<User>> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        User user = userService.register(registerRequestDto);
        return ResponseEntity.ok(
                BaseResponse.<User>builder()
                        .success(true)
                        .message("Kullanıcı kayıt işlemi başarılı...")
                        .code(200)
                        .data(user)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .success(true)
                        .message("Giriş işlemi başarılı...")
                        .code(200)
                        .data(userService.login(loginRequestDto))
                .build());
    }

    @GetMapping("/get-my-profile")
    public ResponseEntity<BaseResponse<User>> getMyProfile(String token) {
        return ResponseEntity.ok(BaseResponse.<User>builder()
                        .success(true)
                        .message("Profil bilgileriniz başarılı şekilde getirildi...")
                        .code(200)
                        .data(userService.getMyProfile(token))
                .build());
    }

    @GetMapping("/find-by-user-name")
    public ResponseEntity<BaseResponse<User>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(
                BaseResponse.<User>builder()
                        .success(true)
                        .message("Kullanıcı bulma işlemi başarı ile gerçekleştirildi...")
                        .code(200)
                        .data(userService.findByUsername(username))
                        .build()
        );
    }



}
