package com.kanchan.Mvc.repository;
import com.kanchan.Mvc.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface ClubRepository extends JpaRepository<Club, Long>
{
        Optional<Club> findByTitle(String url);
        @Query("SELECT C FROM Club C WHERE C.title LIKe CONCAT('%',:query,'%') ")
         List<Club>searchClubs(String query);
    }

