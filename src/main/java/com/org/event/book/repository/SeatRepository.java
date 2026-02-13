package com.org.event.book.repository;

import com.org.event.book.entity.Event;
import com.org.event.book.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Modifying
    @Query("""
           UPDATE Seat s
           SET s.status = :status
           WHERE s.id = :seatId
           """)
    int updateStatus(@Param("seatId") Long seatId,
                     @Param("status") String status);

}

