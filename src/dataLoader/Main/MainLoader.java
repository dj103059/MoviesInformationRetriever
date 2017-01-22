package dataLoader.Main;


import dataLoader.Main.ontologie.Movie;
import dataLoader.Main.ontologie.Type;
import dataLoader.Main.ontologie.team.Actor;
import dataLoader.Main.ontologie.team.Producer;
import dataLoader.Main.ontologie.team.Scriptwriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
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
        Query query = null;
        QueryExecution queryexec = null;
        ResultSet resultSet = null;

        ArrayList<Movie> movies;
        ArrayList<Type> types;
        ArrayList<Scriptwriter> scriptwriters;
        ArrayList<Producer> producers;
        ArrayList<Actor> actors;


        // for movies
        //movies = Movie.constructListOfMovie(query,queryexec,resultSet);
        //movies = Movie.refactorListofMovie(movies);
        /*
        System.out.println("liste des uri movie size = "+movies.size());
        for (Movie movie: movies
                ) {
            System.out.println(movie.getUri());
        }

        // for types
        types = Type.constructListOfType(query,queryexec,resultSet);
        types = Type.refactorListofType(types);

        System.out.println("liste des uri type size = "+types.size());
        for (Type type: types
                ) {
            System.out.println(type.uri);
        }

        // for scriptwriters
        scriptwriters = Scriptwriter.constructListOfscriptwriters(query,queryexec,resultSet);
        scriptwriters = Scriptwriter.refactorListofScriptwriter(scriptwriters);

        System.out.println("liste des uri scriptwriters size = "+scriptwriters.size());
        for (Scriptwriter scriptwriter: scriptwriters
                ) {
            System.out.println(scriptwriter.uri);
        }

        // for producers
        producers = Producer.constructListOfProducer(query,queryexec,resultSet);
        producers = Producer.refactorListofProducer(producers);

        System.out.println("liste des uri producers size = "+producers.size());
        for (Producer producer: producers
                ) {
            System.out.println(producer.uri);
        }*/

        actors = Actor.constructListOfActor(query,queryexec,resultSet);
        actors = Actor.refactorListofActor(actors);
/*
        movies = Movie.fusionType(movies,types);
        movies = Movie.fusionProducer(movies,producers);
        movies = Movie.fusionScriptwriter(movies,scriptwriters);*/

         //   System.out.println("fin ---------------------------------------------");
        //}
       // System.out.println("size : "+movies.size());
       // System.out.println("fin ---------------------------------------------");

       System.out.println("liste des actors size = "+actors.size());
        for (Actor actor: actors
                ) {
            System.out.println(actor.uri);
        }
    }
}
