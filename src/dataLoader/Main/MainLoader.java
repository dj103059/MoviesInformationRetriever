package dataLoader.Main;


import dataLoader.Main.ontologie.Movie;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.log4j.varia.NullAppender;

import java.util.ArrayList;

import static org.apache.jena.query.QueryFactory.*;
/**
 * Created by titanium on 18/01/2017.
 */
public class MainLoader {



    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        Model m = ModelFactory.createDefaultModel();

        ArrayList<Movie> movies;

        movies = Movie.constructListOfMovie();

        for (Movie movie: movies) {
            System.out.println(movie.toString());

        }
        System.out.println("size : "+movies.size());

    }
}
