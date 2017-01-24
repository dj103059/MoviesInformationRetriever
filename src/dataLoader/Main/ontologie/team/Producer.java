package dataLoader.Main.ontologie.team;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Producer {
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?name WHERE"+
            " { <";
    public static String requestpart2 = "> mo:producer ?uriProducer." +
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
    public static ArrayList<Producer> constructListOfProducer(String movieuri, Query q, QueryExecution qr, ResultSet r,Model m){
        String finalRequest = requestpart1 + movieuri + requestpart2;
        //construit la liste de Producer
        ArrayList<Producer> producers = new ArrayList<Producer>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        Producer producertemp;
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            producertemp =  new Producer(movieuri,name.getString());
            producers.add(producertemp);

        }
        return producers;
    }


}
