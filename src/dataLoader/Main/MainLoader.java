package dataLoader.Main;


import dataLoader.Main.ontologie.Characters;
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
import org.apache.jena.util.FileManager;
import org.apache.log4j.varia.NullAppender;

import java.io.InputStream;
import java.util.ArrayList;

import static org.apache.jena.query.QueryFactory.*;
/**
 * Created by titanium on 18/01/2017.
 */
public class MainLoader {




    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());


        // initialisation /////////////////
        Model model;
        final String inputFileName  = "File/RDFS_file/linkedmdb-latest-dump.nt";

        // créer un modèle vide
        model = ModelFactory.createDefaultModel();

        // utiliser le FileManager pour trouver le fichier d'entrée
        InputStream in = FileManager.get().open( inputFileName );

        // lire le fichier owl
        model.read(in,null, "N-TRIPLE");
        System.out.println("fini load dataset");
        //  FIN initialisation /////////////////


        Query query = null;
        QueryExecution queryexec = null;
        ResultSet resultSet = null;

        ArrayList<Movie> movies;
        ArrayList<Type> types;
        ArrayList<Scriptwriter> scriptwriters;
        ArrayList<Producer> producers;
        ArrayList<Actor> actors;


        // for movies
        movies = Movie.constructListOfMovie(query,queryexec,resultSet , model);
        movies = Movie.refactorListofMovie(movies);


        System.out.println("liste des uri movie size = "+movies.size());
        for (Movie movie: movies
                ) {
            movie.addListOfType(Type.constructListOfType(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfScriptwriter(Scriptwriter.constructListOfscriptwriters(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfProducer(Producer.constructListOfProducer(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfActors(Actor.constructListOfActor(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfCharacters(Characters.constructListOfCharacters(movie.getUri(),query,queryexec,resultSet,model));
            System.out.println(movie.toString());
        }

        // for types


        /*System.out.println("liste des uri type size = "+types.size());
        for (Type type: types
                ) {
            System.out.println(type.uri);
        }*/

        // for scriptwriters
       // scriptwriters = Scriptwriter.constructListOfscriptwriters(query,queryexec,resultSet,model);
       // scriptwriters = Scriptwriter.refactorListofScriptwriter(scriptwriters);

        /*System.out.println("liste des uri scriptwriters size = "+scriptwriters.size());
        for (Scriptwriter scriptwriter: scriptwriters
                ) {
            System.out.println(scriptwriter.uri);
        }*/

        // for producers
       // producers = Producer.constructListOfProducer(query,queryexec,resultSet,model);
       // producers = Producer.refactorListofProducer(producers);

        /*System.out.println("liste des uri producers size = "+producers.size());
        for (Producer producer: producers
                ) {
            System.out.println(producer.uri);
        }*/

     //   actors = Actor.constructListOfActor(query,queryexec,resultSet);
      //  actors = Actor.refactorListofActor(actors);

       // movies = Movie.fusionType(movies,types);
      //  movies = Movie.fusionProducer(movies,producers);
      //  movies = Movie.fusionScriptwriter(movies,scriptwriters);

         //   System.out.println("fin ---------------------------------------------");
        //}
       // System.out.println("size : "+movies.size());
       // System.out.println("fin ---------------------------------------------");
/*
       System.out.println("liste des movies size = "+movies.size());
        for (Movie movie: movies
                ) {
            System.out.println(movie.toString());
        }*/
    }
}
