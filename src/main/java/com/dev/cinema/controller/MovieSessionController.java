package com.dev.cinema.controller;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieSessionRequestDto dto) {
        MovieSession movieSession = movieSessionMapper.mapToEntity(dto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessions(@RequestParam Long movieId,
                                                                   @RequestParam LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
