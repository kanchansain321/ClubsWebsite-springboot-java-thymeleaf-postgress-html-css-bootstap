package com.kanchan.Mvc.mapper;

import com.kanchan.Mvc.dto.ClubDto;
import com.kanchan.Mvc.models.Club;

import java.util.List;
import java.util.stream.Collectors;

import static com.kanchan.Mvc.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto club) {
        Club clubDto=Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdon(club.getCreatedon())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }

    public static ClubDto mapToClubDto(Club club)
    {
        ClubDto clubDto=ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdon(club.getCreatedon())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return  clubDto;

    }
}

