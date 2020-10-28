package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto mapToDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setMovieSessionId(ticket.getMovieSession().getId());
        dto.setUserEmail(ticket.getUser().getEmail());
        return dto;
    }
}
