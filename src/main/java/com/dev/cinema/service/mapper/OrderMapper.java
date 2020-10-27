package com.dev.cinema.service.mapper;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.service.UserService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;
    private final UserService userService;

    public OrderMapper(TicketMapper ticketMapper, UserService userService) {
        this.ticketMapper = ticketMapper;
        this.userService = userService;
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
