package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;
    private final UserService userService;

    public TicketMapper(MovieSessionService movieSessionService,
                        MovieSessionMapper movieSessionMapper,
                        UserService userService) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
        this.userService = userService;
    }

    public TicketResponseDto mapToDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setMovieSessionId(ticket.getMovieSession().getId());
        dto.setUserEmail(ticket.getUser().getEmail());
        return dto;
    }
}
