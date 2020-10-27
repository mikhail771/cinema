package com.dev.cinema.dto;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets;
    private String orderDateTime;
    private String userEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
