package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

    @Override
    long count();

    Board findByWriter(String writer);

    List<Board> findByNoIn(List<Long> longs);


    /**
     * sample
     */

    /*List<Board> findByNoAndContent(Long no, String content);

    List<Board> findAllByNoOrContent(Long no, String content);

    List<Board> findByNoBetween(Long startNo, Long endNo);

    List<Board> findAllByNoLessThan(Long no);

    List<Board> findAllByNoGreaterThan(Long no);

    List<Board> findAllByRegDateBefore(Timestamp currentDate);

    List<Board> findAllByRegDateAfter(Timestamp currentDate);

    List<Board> findAllByContentIsNull();

    List<Board> findAllByTitleIsNotNull();

    List<Board> findAllByTitleLike(String title);

    List<Board> findAllByTitleNotLike(String title);

    List<Board> findAllByTitleStartingWith(String title);

    List<Board> findAllByTitleEndingWith(String title);

    List<Board> findAllByTitleContaining(String title);

    List<Board> findAllByNoGreaterThanAndOrderByRegDateDesc(Long no);

    List<Board> findAllByNoNot(Long no);

    List<Board> findAllByNoIn(List<Long> longs);

    List<Board> findAllByNoNotIn(List<Long> longs);*/

    Page<Board> findAllByNoIn(List<Long> longs, Pageable pageable);

    Page<Board> findAllByNoNotIn(List<Long> longs, Pageable pageable);

    @Query
    (
            "select a from Board a where a.no > 0"
    )
    List<Board> selectBoardList();


    @Query
    (
            "select a from Board a where a.no = ?1"
    )
    List<Board> selectBoard(Long no);
}
