package dataLoader.Main.ontologie;

import org.apache.jena.base.Sys;
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
public class Type {
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE"+
            " { <" ;
    public static String requestpart2 =
            "> mo:genre ?urigenre." +
            "?urigenre rdfs:label ?name."+
            " }  ";
    public String uri ;
    public String name ;

    public Type(String uri, String label){
        this.uri = uri;
        this.name = label;
    }

    /**
     * construct list of type
     * @param q
     * @param qr
     * @param r
     * @return list of type for each film
     */
    public static ArrayList<Type> constructListOfType(String urimovie, Query q, QueryExecution qr, ResultSet r,Model m){
        String finalRequest = requestpart1 + urimovie + requestpart2;
        ArrayList<Type> types = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            types.add(new Type(urimovie,name.getString()));
        }
        return types;
    }


}
