package dataLoader.Main.ontologie;

import dataLoader.Main.ontologie.team.Actor;
import dataLoader.Main.ontologie.team.Producer;
import dataLoader.Main.ontologie.team.Scriptwriter;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Movie {
    public static String request = "SELECT DISTINCT ?uri ?label ?date ?duration ?country WHERE"+
            " { ?uri <http://www.w3.org/2000/01/rdf-schema#label> ?label."+
            " ?uri a <http://data.linkedmdb.org/resource/movie/film>. "+
            "?uri <http://data.linkedmdb.org/resource/movie/initial_release_date> ?date."+
            "?uri <http://data.linkedmdb.org/resource/movie/runtime> ?duration."+
            "?uri <http://data.linkedmdb.org/resource/movie/country> ?countrytemp."+
            "?countrytemp <http://www.w3.org/2000/01/rdf-schema#label> ?country."+
            " }  ";
           // + " LIMIT ";



    private String uri ;
    public String country;
    public Type type;
    public ArrayList<Characters> characters = new ArrayList<Characters>();
    public String title;
    public Producer producer;
    public Scriptwriter scriptwriter;
    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public String label ;
    public String date ;
    public float duration;

    /**
     * Constructor
     * @param uri
     * @param label
     */
    public Movie(String uri,String label , String date, float duration, String country){
        this.uri = uri;
        this.label = label;
        this.date = date;
        this.duration = duration;
        this.country = country;
    }


    /**
     * Contruct a list of movies

     * @return list of movies
     */
    //public static ArrayList<Movie> constructListOfMovie(int length){
    public static ArrayList<Movie> constructListOfMovie(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Query query = create(request);
        QueryExecution queryexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
        ResultSet resultSet = queryexec.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution binding = resultSet.nextSolution();
            Literal label = binding.getLiteral("label");
            Literal date = binding.getLiteral("date");
            Literal duration = binding.getLiteral("duration");
            Literal country = binding.getLiteral("country");
            Resource uri = (Resource) binding.get("uri");
            movies.add(new Movie(uri.getURI(),label.getString(),date.getString(),duration.getFloat(),country.getString()));
        }
        return movies;
    }

    public void addActors(ArrayList<Actor> actors){
        this.actors = actors;
    }

    public void addCharacters(ArrayList<Characters> characters){
        this.characters = characters;
    }

    public void addProducer(Producer producer){
        this.producer = producer;
    }

    public void addScriptwriter(Scriptwriter scriptwriter){
        this.scriptwriter = scriptwriter;
    }

   // public void addOriginalCountry(OriginalCountry originalCountry){
  //        this.country = originalCountry;
  //  }

    /**
        getter and setter
     */
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString(){
        return "\n"+" uri : "+uri+ " \n "+
               "label : "+ label + " \n"+
                "date : "+ date+ " \n"+
                "duration : " + duration + " min \n "+
                "country : "+ country +"\n";
    }
}
