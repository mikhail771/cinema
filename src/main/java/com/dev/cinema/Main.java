package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
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
        authenticationService.register("mixmix@gmail.com", "12345");
        System.out.println("Is user logged in with correct password?");
        System.out.println(authenticationService.login(user.getEmail(), "1234"));
        System.out.println("=========HB-06==========");

        Movie movie = new Movie();
        movie.setTitle("MIB");
        movie.setDescription("Fantastic");
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall hall = new CinemaHall();
        hall.setName("Grand");
        hall.setCapacity(150);
        cinemaHallService.add(hall);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setSessionTime(LocalDateTime.of(2020, 10, 14, 12, 00));
        movieSession.setCinemaHall(hall);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        User user1 = userService.findByEmail("mixmix@gmail.com").get();
        shoppingCartService.addSession(movieSession, user1);
        System.out.println(shoppingCartService.getByUser(user1));
    }
}
