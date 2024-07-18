package com.kanchan.Mvc.service.impl;

import com.kanchan.Mvc.dto.ClubDto;
import com.kanchan.Mvc.models.Club;
import com.kanchan.Mvc.repository.ClubRepository;
import com.kanchan.Mvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kanchan.Mvc.mapper.ClubMapper.mapToClub;
import static com.kanchan.Mvc.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceimpl implements ClubService {
    private ClubRepository clubRepository;
    @Autowired
    public ClubServiceimpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club>clubs=clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    public ClubDto findClubById(long clubId) {
        Club club =clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }


    public void updateClub(ClubDto clubDto) {
      Club club=mapToClub(clubDto);
      clubRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
        public List<ClubDto> findAllClub() {
            List<Club> clubs = clubRepository.findAll();
            return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
        }
        public Club saveClub(ClubDto clubDto){
            Club club=mapToClub(clubDto);
            return clubRepository.save(club);
        }
        }


