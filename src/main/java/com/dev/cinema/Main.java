package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("The Avengers");
        movie.setDescription("Marvel's The Avengers[6]");
        movieService.add(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("MIB");
        movie2.setDescription("War with aliens");
        movieService.add(movie2);

        System.out.println("==========Poster==========");
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cityHall = new CinemaHall();
        cityHall.setCapacity(100);
        cityHall.setName("City Hall");
        cinemaHallService.add(cityHall);

        CinemaHall movieTower = new CinemaHall();
        movieTower.setCapacity(120);
        movieTower.setName("Movie Tower");
        cinemaHallService.add(movieTower);

        System.out.println("================Cinema theaters=================");
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession session1 = new MovieSession();
        session1.setCinemaHall(cityHall);
        session1.setMovie(movie);
        session1.setSessionTime(LocalDateTime.of(2020, 11, 13, 12, 00));
        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(session1);

        MovieSession session2 = new MovieSession();
        session2.setCinemaHall(movieTower);
        session2.setMovie(movie2);
        session2.setSessionTime(LocalDateTime.of(2020, 11, 13, 15, 00));
        movieSessionService.add(session2);

        MovieSession session3 = new MovieSession();
        session3.setCinemaHall(cityHall);
        session3.setMovie(movie);
        session3.setSessionTime(
                LocalDateTime.of(2020, 11, 14, 12, 00));
        movieSessionService.add(session3);

        MovieSession session4 = new MovieSession();
        session4.setCinemaHall(cityHall);
        session4.setMovie(movie);
        session4.setSessionTime(
                LocalDateTime.of(2020, 11, 13, 21, 00));
        movieSessionService.add(session4);

        System.out.println("===========Sessions==============");
        movieSessionService.findAvailableSessions(1L,
                LocalDate.of(2020, 11, 13)).forEach(System.out::println);
    }
}
