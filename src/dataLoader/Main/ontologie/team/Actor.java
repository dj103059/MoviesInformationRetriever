package dataLoader.Main.ontologie.team;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Actor {
    public String uri ;
    public static String request = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?uri ?name WHERE"+
            " { ?uri rdfs:label ?label."+
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?country." +
            "?uri mo:genre ?genre." +
            "?uri mo:writer ?uriWriter." +
            "?uri mo:producer ?uriProducer." +
                "{" +
                    "SElECT DISTINCT ?name WHERE { "+
                        "?uri mo:actor ?uriactor." +
                        "?uriactor rdfs:label ?name." +
                     "} LIMIT 2" +
                "}" +
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
    public static ArrayList<Actor> constructListOfActor(Query q, QueryExecution qr, ResultSet r){
        ArrayList<Actor> actors = new ArrayList<>();
        q = create(request);
        qr = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", q);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            Resource uri = (Resource) binding.get("uri");
            actors.add(new Actor(uri.getURI(),name.getString()));
        }
        return actors;
    }


    public static ArrayList<Actor> refactorListofActor(ArrayList<Actor> actors){
        for (int i = 0; i< actors.size();i++) {
            if(i != 0){
                while(Objects.equals(actors.get(i).uri, actors.get(i - 1).uri) && i < (actors.size()-1)){
                    actors.get(i-1).name += ", "+actors.get(i).name;
                    actors.remove(i);
                }
            }
        }

        if(Objects.equals(actors.get(actors.size() - 2).uri, actors.get(actors.size() - 1).uri)){
            actors.get(actors.size()-2).name += ", "+actors.get(actors.size()-1).name;
            actors.remove(actors.size()-1);
        }
        return actors;
    }
}

