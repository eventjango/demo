package com.example.demo;


import com.example.demo.domain.Member;
import com.example.demo.domain.Profile;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProfileRepository;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "local")
public class ProfileTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;


    @Test
    @Commit
    public void insertMembers(){

        IntStream
        .range(0, 5)
        .forEach(
                n -> {

                    Member member = new Member();
                    member.setId("user" + n);
                    member.setPassword("password" + n);
                    member.setName("kevin" + n);

                    memberRepository.save(member);
                }
        );

        memberRepository.findAll()
        .listIterator().forEachRemaining(System.out::println);

        assertEquals(0, profileRepository.count());
    }

    @Test
    @Commit
    public void insertProfile(){

        Member member = memberRepository.findOne("user0");

        assertNotNull(member);

        IntStream
                .range(0, 5)
                .forEach(
                        n -> {

                            Profile profile = new Profile();
                            profile.setName("face" + n + "".concat(".jpg"));

                            if(n == 0) profile.setCurrent(true);

                            profile.setMember(member);

                            profileRepository.save(profile);
                        }
                );

        profileRepository.findAll()
                .forEach(
                        profile -> {

                            System.out.println(profile.getNo());
                            System.out.println(profile.getName());
                            System.out.println(profile.getRegDate());
                            System.out.println(profile.getMember().getName());
                        }
                );
    }


    @Test
    @Transactional(readOnly = true)
    public void selectMembers(){

        @Data
        class MemberAndProfile {

            Member member;
            Profile profile;

            MemberAndProfile(Member member, Profile profile){

                this.member = member;
                this.profile = profile;
            }
        }

        Member member = memberRepository.findOne("user0");

        System.out.println(
                new MemberAndProfile(member, profileRepository.findByMemberAndCurrentTrue(member))
        );
    }
}
