package com.coupong.controller;

import com.coupong.member.dto.MemberLoginDto;
import com.coupong.member.dto.MemberSignupDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String testEmail = "test@email.com";
    private String testPass = "test pass";
    private String testNick = "test nick";
    private String testPhone = "01012341234";

    @Test
    public void 회원가입() throws Exception{
        String content = objectMapper.writeValueAsString(
                MemberSignupDto.builder()
                        .email(testEmail)
                        .password(testPass)
                        .nickname(testNick)
                        .phoneNumber(testPhone)
                        .build()
        );
        mockMvc.perform(post("/member/sign-up")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인() throws Exception{
        signup();

        String content = objectMapper.writeValueAsString(
                MemberLoginDto.builder()
                        .email(testEmail)
                        .password(testPass)
                        .build()
        );

        mockMvc.perform(post("/member/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인실패() throws Exception{
        signup();
        String content = objectMapper.writeValueAsString(
                MemberLoginDto.builder()
                        .email(testEmail)
                        .password("wrong pass")
                        .build()
        );

        mockMvc.perform(post("/member/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    public void signup() throws Exception{
        String content = objectMapper.writeValueAsString(
                MemberSignupDto.builder()
                        .email(testEmail)
                        .password(testPass)
                        .nickname(testNick)
                        .phoneNumber(testPhone)
                        .build()
        );
        mockMvc.perform(post("/member/sign-up")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}