package com.practice.shoutreview.repositories;

import com.practice.shoutreview.entities.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeat,Integer> {
}
