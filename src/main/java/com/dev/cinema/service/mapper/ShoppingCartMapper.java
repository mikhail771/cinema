package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.ShoppingCart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto mapToDto(ShoppingCart cart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto(cart.getId());
        List<TicketResponseDto> ticketDtos = cart.getTickets().stream()
                .map(ticketMapper::mapToDto)
                .collect(Collectors.toList());
        dto.setTickets(ticketDtos);
        return dto;
    }
}
