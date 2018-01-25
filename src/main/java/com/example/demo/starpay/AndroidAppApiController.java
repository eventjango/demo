package com.example.demo.starpay;/*
package artec.starapp.controller;


import artec.base.abstracts.StarpayAbstractController;
import artec.base.module.StarCoinConnector;
import artec.base.oauth2.MemberValidator;
import artec.base.oauth2.TokenBuilder;
import artec.starapp.repository.old.*;
import artec.starapp.service.old.MemberService;
import artec.starapp.vo.AppVersionVO;
import artec.starapp.vo.MemberAppInfoVO;
import artec.starapp.vo.MemberVO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test-api")
public class AndroidAppApiController extends StarpayAbstractController{

    @Autowired
    private StarCoinConnector starCoinConnector;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAppInfoRepository memberAppInfoRepository;

    @Autowired
    private AppVersionRepository appVersionRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CoinMoveLogRepository coinMoveLogRepository;


    */
/**
     * app version look up
     * @return
     *//*

    @GetMapping("app/version")
    public AppVersionVO version(){

        return appVersionRepository.findOne((long)1);
    }


    */
/**
     * log in
     * @param memberVO
     * @param memberAppInfoVO
     * @return
     *//*

    @PostMapping("login")
    public Map<String, Object> memberLogin(MemberVO memberVO, MemberAppInfoVO memberAppInfoVO){

        //----------------- 먼저 사용자 신분을 확인한 다음 엑세스 코드를 입력받는다


        MemberVO member = memberRepository.findByEmail(memberVO.getEmail());
        MemberValidator memberValidator = new MemberValidator(memberRepository, memberAppInfoRepository, passwordEncoder);

        return memberValidator.valid(memberVO, memberAppInfoVO);
    }


    */
/**
     * access code
     * @param memberVO
     * @param memberAppInfoVO
     * @return
     *//*

    @PostMapping("access")
    public Map<String, Object> memberAccessCode(MemberVO memberVO, MemberAppInfoVO memberAppInfoVO) throws IOException, JSONException {

        //--------------------- 클라이언트 계정 + 사용자 계정으로 융합된 토큰을 발급 받는다.

        MemberVO member = memberRepository.findByEmail(memberVO.getEmail());
        MemberValidator memberValidator = new MemberValidator(memberRepository, memberAppInfoRepository, passwordEncoder);

        Map<String, Object> result = memberValidator.valid(memberVO, memberAppInfoVO);

        if(!member.getAccessCode().equals(memberVO.getAccessCode())){

            result.put("error", true);
            result.put("errorcode", -4);
            result.put("message", "엑세스코드 불일치");
            result.put("result", null);

            return result;
        }

        String authorization = "Bearer ".concat(TokenBuilder.build(memberVO).getString("access_token"));
        result.put("authorization", authorization);

        return result;
    }


    */
/**
     * 잔액 조회
     * @param walletAccount
     * @return
     *//*

    @GetMapping("coin/balance")
    public Map<String, Double> lookupBalance(@RequestParam("walletAccount") String walletAccount){

        Map<String, Double> result = new HashMap<>();
        result.put("result", starCoinConnector.getBalance(walletAccount));

        return result;
    }


    @GetMapping("coin/test")
    public String test(){

        return "Hello Test";
    }
}
*/
