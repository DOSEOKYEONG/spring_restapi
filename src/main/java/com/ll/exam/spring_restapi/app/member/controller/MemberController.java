package com.ll.exam.spring_restapi.app.member.controller;

import com.ll.exam.spring_restapi.app.base.dto.RsData;
import com.ll.exam.spring_restapi.app.member.entity.Member;
import com.ll.exam.spring_restapi.app.member.service.MemberService;
import com.ll.exam.spring_restapi.app.security.entity.MemberContext;
import com.ll.exam.spring_restapi.app.security.jwt.JwtProvider;
import com.ll.exam.spring_restapi.app.util.Util;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Data
    public static class LoginDto {
        private String username;
        private String password;

        public boolean isNotValid() {
            return username == null || password == null || username.trim().length() == 0 | username.trim().length() == 0;
        }
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext){
        return "안녕";
    }

    @PostMapping("/login")
    public ResponseEntity<RsData> login(@RequestBody LoginDto loginDto) {
        if (loginDto.isNotValid()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "로그인 정보가 올바르지 않습니다."), null);
        }

        Member member = memberService.findByUsername(loginDto.getUsername()).orElse(null);

        if (member == null) {
            return Util.spring.responseEntityOf(RsData.of("F-2", "해당 회원이 존재하지 않습니다."), null);
        }

        if (passwordEncoder.matches(loginDto.getPassword(), member.getPassword()) == false) {
            return Util.spring.responseEntityOf(RsData.of("F-3", "비밀번호가 올바르지 않습니다."), null);
        }

        String accessToken = memberService.geAccessToken(member);

        return Util.spring.responseEntityOf(
                RsData.of(
                        "S-1",
                        "로그인 성공, Access Token을 발급합니다.",
                        Util.mapOf("accessToken", accessToken)),
                Util.spring.httpHeadersOf("Authentication", accessToken)
        );
    }
}
