/*
package com.example.demo.starpay;

import artec.base.security.vo.MemberDetails;
import artec.starapp.model.Member;
import artec.starapp.repository.MemberRepository;
import artec.starapp.repository.MemberRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findOneByEmail(username);

        if(member == null)
            throw new UsernameNotFoundException("No user present with email : " + username);

        else return new MemberDetails(member, memberRoleRepository.findRoleByMemberId(member.getId()));
    }

}
*/
