package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.MovieSessionResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto mapToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setName(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getSessionTime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return dto;
    }

    public MovieSession mapToEntity(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setSessionTime(LocalDateTime.parse(dto.getShowTime(),
                DateTimeFormatter.ISO_DATE_TIME));
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        return movieSession;
    }
}
