package com.example.demo.repository;

import com.example.demo.domain.FreeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Long> {

}
