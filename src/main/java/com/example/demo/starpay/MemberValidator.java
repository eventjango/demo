package com.example.demo.starpay;/*
package artec.base.oauth2;

import artec.starapp.repository.MemberRepository;
import artec.starapp.model.MemberUserAppInfo;
import artec.starapp.model.Member;
import artec.starapp.repository.MemberUserAppInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class MemberValidator {

    private final MemberRepository memberRepository;

    private final MemberUserAppInfoRepository memberAppInfoRepository;

    private final PasswordEncoder passwordEncoder;

    public MemberValidator(MemberRepository memberRepository, MemberUserAppInfoRepository memberAppInfoRepository, PasswordEncoder passwordEncoder){

        this.memberRepository = memberRepository;
        this.memberAppInfoRepository = memberAppInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> valid(Member member, MemberUserAppInfo memberAppInfo){

        Map<String, Object> resultMap = new HashMap<>();

        Member retrieveMember = memberRepository.findOneByEmail(member.getEmail());

        if(retrieveMember == null){

            resultMap.put("error", true);
            resultMap.put("errorcode", -1);
            resultMap.put("message", null);
            resultMap.put("result", null);

            return resultMap;
        }

        if(!retrieveMember.isAuth()){

            resultMap.put("error", true);
            resultMap.put("errorcode", -2);
            resultMap.put("message", "이메일 인증을 받지 않음");
            resultMap.put("result", null);

            return resultMap;
        }

        if(passwordEncoder.matches(member.getPassword(), retrieveMember.getPassword())){

            resultMap.put("error", true);
            resultMap.put("errorcode", -3);
            resultMap.put("message", "패스워드 불일치");
            resultMap.put("result", null);

            return resultMap;
        }

        MemberUserAppInfo appInfoVO = memberAppInfoRepository.findByMemberIdAndDeviceType(retrieveMember.getMemberId(), memberAppInfo.getDeviceType());

        if(appInfoVO == null){

            memberAppInfo.setMemberId(retrieveMember.getMemberId());
            memberAppInfoRepository.save(memberAppInfo);

        }else{

            memberAppInfo.setMemberId(retrieveMember.getMemberId());
            memberAppInfo.setMaiId(appInfoVO.getMaiId());
            memberAppInfoRepository.save(memberAppInfo);
        }

        retrieveMember = memberRepository.findByEmail(member.getEmail());
        resultMap.put("error", false);
        resultMap.put("result", retrieveMember);

        return resultMap;
    }
}
*/
