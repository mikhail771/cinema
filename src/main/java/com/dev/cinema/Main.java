package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import javax.naming.AuthenticationException;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        User user = new User();
        user.setEmail("asdf@gmail.com");
        user.setLogin("Alf");
        user.setPassword("1234");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(user);
        System.out.println("Get user by email:");
        System.out.println(userService.findByEmail("asdf@gmail.com"));
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        System.out.println("Is user logged in with correct password?");
        System.out.println(authenticationService.login(user.getEmail(), "1234"));

        System.out.println("Is user logged in with INCORRECT password?");
        System.out.println(authenticationService.login(user.getEmail(), "BLA-BLA"));
    }
}
