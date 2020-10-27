package com.dev.cinema.controller;

import com.dev.cinema.dto.CinemaHallRequestDto;
import com.dev.cinema.dto.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.mapper.CinemaHallMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public CinemaHall add(@RequestBody CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = cinemaHallMapper.mapDtoToEntity(dto);
        cinemaHallService.add(cinemaHall);
        return cinemaHall;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
