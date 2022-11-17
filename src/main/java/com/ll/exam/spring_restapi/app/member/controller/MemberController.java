package com.ll.exam.spring_restapi.app.member.controller;

import com.ll.exam.spring_restapi.app.base.dto.RsData;
import com.ll.exam.spring_restapi.app.member.dto.request.LoginDto;
import com.ll.exam.spring_restapi.app.member.entity.Member;
import com.ll.exam.spring_restapi.app.member.service.MemberService;
import com.ll.exam.spring_restapi.app.security.entity.MemberContext;
import com.ll.exam.spring_restapi.app.security.jwt.JwtProvider;
import com.ll.exam.spring_restapi.app.util.Util;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "로그인 기능과 로그인 된 회원의 정보를 제공 기능을 담당하는 컨트롤러")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext){
        return "안녕";
    }

    @GetMapping("/me")
    public ResponseEntity<RsData> me(@AuthenticationPrincipal MemberContext memberContext){
        if (memberContext == null) {
            return Util.spring.responseEntityOf(RsData.failOf(null));
        }

        return Util.spring.responseEntityOf(RsData.successOf(memberContext));
    }

    @PostMapping("/login")
    public ResponseEntity<RsData> login(@Valid @RequestBody LoginDto loginDto) {
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
