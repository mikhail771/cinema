package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.Order;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto mapToDto(Order order) {

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticketMapper::mapToDto)
                .collect(Collectors.toList());
        dto.setTickets(tickets);
        dto.setOrderDateTime(
                order.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setUserEmail(order.getUser().getEmail());
        return dto;
    }
}
