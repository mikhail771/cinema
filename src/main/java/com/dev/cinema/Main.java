package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("The Avengers");
        movie.setDescription("Marvel's The Avengers[6] "
                + "(classified under the name Marvel Avengers Assemble "
                + "in the United Kingdom and Ireland[3][7]), or simply "
                + "The Avengers, is a 2012 American superhero film based on "
                + "the Marvel Comics superhero team of the same name. ");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
