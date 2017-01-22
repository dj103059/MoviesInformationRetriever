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
public class Producer {
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
            "?uriProducer rdfs:label ?name." +

            " } ";
    public String uri ;
    public String name;

    public Producer(String uri, String name){
        this.uri = uri;
        this.name = name;
    }



    /**
     * Contruct a list of producers
     * @return list of producers
     */
    public static ArrayList<Producer> constructListOfProducer(Query q, QueryExecution qr, ResultSet r){
        //construit la liste de Producer
        ArrayList<Producer> producers = new ArrayList<Producer>();
        q = create(request);
        qr = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", q);
        r = qr.execSelect();
        Producer producertemp;
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            Resource uri = (Resource) binding.get("uri");
            producertemp =  new Producer(uri.getURI(),name.getString());
            producers.add(producertemp);

        }
        return producers;
    }

    public static ArrayList<Producer> refactorListofProducer(ArrayList<Producer> producers){
        for (int i = 0; i< producers.size();i++) {
            if(i != 0){
                while(Objects.equals(producers.get(i).uri, producers.get(i - 1).uri) && i < (producers.size()-1)){
                    producers.get(i-1).name += ", "+producers.get(i).name;
                    producers.remove(i);
                }
            }
        }

        if(Objects.equals(producers.get(producers.size() - 2).uri, producers.get(producers.size() - 1).uri)){
            producers.get(producers.size()-2).name += ", "+producers.get(producers.size()-1).name;
            producers.remove(producers.size()-1);
        }
        return producers;
    }
}
