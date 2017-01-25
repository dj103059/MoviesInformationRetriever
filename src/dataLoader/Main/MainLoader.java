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
       // model.read(in,null, "N-TRIPLE");
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
        /*movies = Movie.constructListOfMovie(query,queryexec,resultSet , model);
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
        }*/
/////CODE POUR AJOUTER + MODIFIER LE FICHIER OWL
        Model m = ModelFactory.createDefaultModel();

        InputStream test = FileManager.get().open("/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie.owl");
        m.read(test, "RDF/XML");
        String NS="http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";

        Resource r = m.createResource(NS+"http://data.linkedmdb.org/resource/film/2676");//like subject
        Property p1 =m.createProperty(NS+"Duration");
        Property p2 =m.createProperty(NS+"wasReleasedIn");
        Property p3 =m.createProperty("http://www.w3.org/2000/01/rdf-schema#label");
        Property p4 =m.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        r.addProperty(p1, "93.0 min", XSDDatatype.XSDfloat);
        r.addProperty(p2, "1928", XSDDatatype.XSDstring);
        r.addProperty(p3, "Champagne", XSDDatatype.XSDstring);
        r.addProperty(p4, NS+"Movie");

        try {
            m.write(new FileOutputStream("/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie1.owl"), "RDF/XML");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////

        /*
        Model m = ModelFactory.createDefaultModel();
        m.read("/Users/heshanjayasinghe/Documents/A-enigmaProject/jena_Enigma/src/jena_enigma/Enigma.RDF", "RDF/XML");
        String NS="http://www.heshjayasinghe.webatu.com/Enigma.RDF#";

        Resource r = m.createResource(NS+"user8");//like subject
        Property p1 =m.createProperty(NS+"lname");
        Property p2 =m.createProperty(NS+"email");
        Property p3 =m.createProperty(NS+"fname");
        Property p4 =m.createProperty(NS+"password");


        r.addProperty(p1, "thathasara", XSDDatatype.XSDstring);
        r.addProperty(p2, "nt@gmail.com", XSDDatatype.XSDstring);
        r.addProperty(p3, "nipun", XSDDatatype.XSDstring);
        r.addProperty(p4, "t123", XSDDatatype.XSDstring);
      //   m.write(System.out,"thurtle");
          m.write(new FileOutputStream("/Users/heshanjayasinghe/Documents/A-enigmaProject/jena_Enigma/src/jena_enigma/Enigma.RDF"), "RDF/XML");
         */
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
