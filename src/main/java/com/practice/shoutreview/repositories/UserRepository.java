package com.practice.shoutreview.repositories;

import com.practice.shoutreview.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByMobile(String mobile);

    User findByName(String name);
}
