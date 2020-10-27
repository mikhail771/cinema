package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private OrderService orderService;
    private OrderMapper orderMapper;

    public OrderController(ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderService orderService,
                           OrderMapper orderMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public Order completeOrder(@RequestParam String email) {
        User user = userService.findByEmail(email).get();
        ShoppingCart cart = shoppingCartService.getByUser(user);
        return orderService.completeOrder(cart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.findById(userId).get()).stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
