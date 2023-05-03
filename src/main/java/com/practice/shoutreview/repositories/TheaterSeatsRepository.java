package com.practice.shoutreview.repositories;

import com.practice.shoutreview.entities.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatsRepository extends JpaRepository<TheaterSeats,Integer> {
}
