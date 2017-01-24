package dataLoader.Main.ontologie;

import dataLoader.Main.ontologie.team.Actor;
import dataLoader.Main.ontologie.team.Producer;
import dataLoader.Main.ontologie.team.Scriptwriter;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
//import sun.plugin.perf.PluginRollup;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Movie {
    public static String request = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?uri ?label  ?date  ?duration    ?country WHERE"+
            " { ?uri rdfs:label ?label."+
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?country." +
            "?uri mo:genre ?genre." +
            "?uri mo:writer ?uriWriter." +
            "?uri mo:producer ?uriProducer." +
           // "?uri mo:actor ?uriactor." +
           // "?uri mo:performance ?uriperfomance."+
            " } ";



    private String uri ;
    public String country;
    public String title;
    public ArrayList<Type> types = new ArrayList<Type>();
    public ArrayList<Producer> producers = new ArrayList<Producer>();
    public ArrayList<Scriptwriter> writers = new ArrayList<Scriptwriter>();
    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public ArrayList<Characters> characters = new ArrayList<Characters>();
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
     * Query query ;
    QueryExecution queryexec ;//= QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
    ResultSet
     */
    //public static ArrayList<Movie> constructListOfMovie(int length){
    public static ArrayList<Movie> constructListOfMovie(Query q, QueryExecution qr, ResultSet r, Model m){
        //construit la liste de film
        ArrayList<Movie> movies = new ArrayList<Movie>();
        q = create(request);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        Movie movietemp;
        //System.out.println("voici ma requête" +request);
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal label = binding.getLiteral("label");
            Literal date = binding.getLiteral("date");
            Literal duration = binding.getLiteral("duration");
            Literal country = binding.getLiteral("country");
            Resource uri = (Resource) binding.get("uri");
            movietemp =  new Movie(uri.getURI(),label.getString(),date.getString(),duration.getFloat(),country.getString());
            movies.add(movietemp);

        }
        return movies;
    }

    public static ArrayList<Movie> refactorListofMovie(ArrayList<Movie> movies){
        for (int i = 0; i< movies.size();i++) {
            if(i != 0){
                while(Objects.equals(movies.get(i).getUri(), movies.get(i - 1).getUri()) && i < (movies.size()-1)){
                    movies.remove(i);
                }
            }
        }
        if(Objects.equals(movies.get(movies.size() - 2).getUri(), movies.get(movies.size() - 1).getUri())){
            movies.remove(movies.size()-1);
        }
        return movies;
    }


    public void addListOfCharacters(ArrayList<Characters> characterses){
        this.characters =characterses;
    }


    public void addListOfProducer(ArrayList<Producer> producers){
        this.producers =producers;
    }

    public void addListOfScriptwriter(ArrayList<Scriptwriter> scriptwriters){
        this.writers = scriptwriters;
    }

    public void addListOfType(ArrayList<Type> listoftypes){
        this.types = listoftypes;
    }

    public void addListOfActors(ArrayList<Actor> listofactors){
        this.actors = listofactors;
    }
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
        String firstsentence = "\n"+" uri : "+this.uri+ " \n "+
                "label : "+ this.label + " \n"+
                "date : "+ this.date+ " \n"+
                "duration : " + this.duration + " min \n "+
                "country : "+ this.country +" \n" +
                "genre : ";

        for(int i = 0;i<this.types.size();i++){
            firstsentence += this.types.get(i).name+" ,";
        }
        firstsentence += "\n producer : ";
        for(int j = 0;j<this.producers.size();j++){
            firstsentence += this.producers.get(j).name+" ,";
        }
        firstsentence += "\n writer : ";
        for(int k = 0;k<this.writers.size();k++){
            firstsentence += this.writers.get(k).name+" ,";
        }
        firstsentence += "\n actor : ";
        for(int k = 0;k<this.actors.size();k++){
            firstsentence += this.actors.get(k).name+" ,";
        }
        firstsentence += "\n character : ";
        for(int k = 0;k<this.characters.size();k++){
            firstsentence += this.characters.get(k).name+" ,";
        }
        return  firstsentence;

    }
}
