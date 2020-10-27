package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.CinemaHallRequestDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    private final CinemaHallService cinemaHallService;

    public CinemaHallMapper(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    public CinemaHall mapDtoToEntity(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setName(dto.getName());
        cinemaHall.setCapacity(dto.getCapacity());
        return cinemaHall;
    }

    public CinemaHallResponseDto mapEntityToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setId(cinemaHall.getId());
        dto.setName(cinemaHall.getName());
        dto.setCapacity(cinemaHall.getCapacity());
        return dto;
    }
}
