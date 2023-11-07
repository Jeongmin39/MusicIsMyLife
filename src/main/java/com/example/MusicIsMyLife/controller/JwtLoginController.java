package com.example.MusicIsMyLife.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import com.example.MusicIsMyLife.dto.JoinRequestDTO;
import com.example.MusicIsMyLife.dto.LoginRequestDTO;
import com.example.MusicIsMyLife.entity.UserEntity;
import com.example.MusicIsMyLife.jwt.JwtTokenUtil;
import com.example.MusicIsMyLife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class JwtLoginController {

    private final UserService userService;

    @GetMapping("/jwt/join")
    public String joinPage() {
        return "jwt/join";
    }

    @PostMapping("/jwt/join")
    public String join(JoinRequestDTO joinRequestDTO) {

        System.out.println("data");

        // userId 중복 체크
        if (userService.checkUserIdDuplicate(joinRequestDTO.getUserId())) {
            return "중복인 아이디가 존재합니다.";
        }

        // password와 passwordCheck가 같은지 체크
        if (!joinRequestDTO.getUserPassword().equals(joinRequestDTO.getUserPasswordCheck())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        userService.joinEncode(joinRequestDTO);
        System.out.println("LoginController.save");
        System.out.println("joinRequestDTO = " + joinRequestDTO);
        return "index";
    }

    @GetMapping("/jwt/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequestDTO", new LoginRequestDTO());
        return "jwt/login";
    }

    @PostMapping("/jwt/login")
    public String login(LoginRequestDTO loginRequestDTO, BindingResult bindingResult,
                        HttpServletResponse response) {
        UserEntity userEntity = userService.login(loginRequestDTO);
        System.out.println("컨트롤러에서 userEntity " + userEntity); //여기까진 ok

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if (userEntity == null) {
            System.out.println("userEntity가 null?");
            return "index";
        }

        // 로그인 성공 => Jwt Token 발급

        String secretKey = "my-secret-key-123123";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        String jwtToken = JwtTokenUtil.createToken(userEntity.getUserId(), secretKey, expireTimeMs);
        Cookie cookie = new Cookie("jwtToken", jwtToken);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        return "calendar";
    }

    @GetMapping("/jwt/info")
    public String userInfo(Authentication auth) {
        UserEntity loginUser = userService.getLoginUserByUserId(auth.getName());

        return String.format("userId : %s\npassword: %s\nrole : %s",
                loginUser.getUserId(), loginUser.getUserPassword(), loginUser.getRole().name());
    }

    @GetMapping("/jwt/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}
