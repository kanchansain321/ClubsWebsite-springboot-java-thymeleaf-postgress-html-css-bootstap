package com.kanchan.Mvc.service;

import com.kanchan.Mvc.dto.ClubDto;
import com.kanchan.Mvc.models.Club;

import java.util.List;
public interface ClubService {
    List<ClubDto> findAllClub();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(long clubId);
    void updateClub(ClubDto club);

    void delete(long clubId);
    List<ClubDto>searchClubs(String query);
}
