package com.example.demo.repository;


import com.example.demo.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{


    Content findByName(String name);


    @Modifying
    @Query
    (
            "update File f set f.name = ?2 where f.id = ?1"
    )
    int updateFileName(Long fileId, String newFileName);
}
