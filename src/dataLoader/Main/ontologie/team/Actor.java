package dataLoader.Main.ontologie.team;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Model;
import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Actor {
    public String uri ;
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE"+
            " {  <";

    public static String requestpart2 = "> mo:actor ?uriactor." +
                        "?uriactor rdfs:label ?name." +
            " } ";


    public String name ;

    public Actor(String uri, String label){
        this.uri = uri;
        this.name = label;
    }

    /**
     * construct list of actor
     * @param q
     * @param qr
     * @param r
     * @return list of actor for each film
     */
    public static ArrayList<Actor> constructListOfActor(String urimovie, Query q, QueryExecution qr, ResultSet r, Model m){
        String finalRequest = requestpart1 + urimovie + requestpart2;
        ArrayList<Actor> actors = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            actors.add(new Actor(urimovie,name.getString()));
        }
        return actors;
    }



}

