package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByMember(Member member);

    Profile findByMemberAndCurrentTrue(Member member);
}
