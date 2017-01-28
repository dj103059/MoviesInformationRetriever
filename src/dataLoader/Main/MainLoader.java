package dataLoader.Main;


import dataLoader.Main.ontologie.Characters;
import dataLoader.Main.ontologie.Movie;
import dataLoader.Main.ontologie.Type;
import dataLoader.Main.ontologie.team.Actor;
import dataLoader.Main.ontologie.team.Producer;
import dataLoader.Main.ontologie.team.Scriptwriter;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.log4j.varia.NullAppender;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

        //load of our ontologie
        Model m = ModelFactory.createDefaultModel();
        final String inputFile  = "/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie.owl";

        InputStream test = FileManager.get().open(inputFile);
        m.read(test, "RDF/XML");
        System.out.println("fini load ontologie");

        ///////////////////////////////////

        Query query = null;
        QueryExecution queryexec = null;
        ResultSet resultSet = null;

        ArrayList<Movie> movies;

        ArrayList<Type> types;
        ArrayList<Producer> producers;
        ArrayList<Scriptwriter> scriptwriters;
        ArrayList<Actor> actors;
        ArrayList<Characters> characterses;

        System.out.println("Create Character list");
        characterses = Characters.constructListOfCharacter(query,queryexec,resultSet,model);

        System.out.println("Create producer list");
        producers = Producer.constructListOfProducer(query,queryexec,resultSet,model);

        System.out.println("Create type list");
        types = Type.constructListOfType(query,queryexec,resultSet,model);

        System.out.println("Create writer list");
        scriptwriters = Scriptwriter.constructListOfWriter(query,queryexec,resultSet,model);

        System.out.println("Create actor list");
        actors = Actor.constructListOfActor(query,queryexec,resultSet,model);

        System.out.println("Create movie list");
        movies =  loadMovieFromBigDataset(model);

        System.out.println("add all  to ontologie");
        Movie.fillOntologie(movies,types,actors,characterses,producers,scriptwriters);

        System.out.println("fini");

    }



    /**
     * load file from dump and return an arrays of movies
     * @param model
     * @return
     */
    public static ArrayList<Movie> loadMovieFromBigDataset(Model model){
        Query query = null;
        QueryExecution queryexec = null;
        ResultSet resultSet = null;
        ArrayList<Movie> movies;
        movies = Movie.constructListOfMovie(query,queryexec,resultSet , model);
        movies = Movie.refactorListofMovie(movies);
        for (Movie movie: movies
                ) {
            movie.addListOfType(Type.constructListOfTypeForMovie(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfScriptwriter(Scriptwriter.constructListOfscriptwritersForMovie(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfProducer(Producer.constructListOfProducerForMovie(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfActors(Actor.constructListOfActorForMovie(movie.getUri(),query,queryexec,resultSet,model));
            movie.addListOfCharacters(Characters.constructListOfCharactersForMovie(movie.getUri(),query,queryexec,resultSet,model));
           // System.out.println(movie.toString());
        }
        return  movies;
    }
}
