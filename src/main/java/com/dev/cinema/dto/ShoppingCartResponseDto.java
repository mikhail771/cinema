package com.dev.cinema.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userId;
    private List<TicketResponseDto> tickets;

    public ShoppingCartResponseDto() {
    }

    public ShoppingCartResponseDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
