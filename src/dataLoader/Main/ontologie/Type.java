package dataLoader.Main.ontologie;

import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Type {
    public static String request = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?uri ?name WHERE"+
            " { ?uri rdfs:label ?label."+
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?country." +
            "?uri mo:genre ?urigenre." +
            "?urigenre rdfs:label ?name."+
            "?uri mo:writer ?uriWriter." +
            "?uri mo:producer ?uriProducer." +
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
    public static ArrayList<Type> constructListOfType(Query q, QueryExecution qr, ResultSet r){
        ArrayList<Type> types = new ArrayList<>();
        q = create(request);
        qr = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", q);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            Resource uri = (Resource) binding.get("uri");
            types.add(new Type(uri.getURI(),name.getString()));
        }
        return types;
    }


    public static ArrayList<Type> refactorListofType(ArrayList<Type> types){
        for (int i = 0; i< types.size();i++) {
            if(i != 0){
                while(Objects.equals(types.get(i).uri, types.get(i - 1).uri) && i < (types.size()-1)){
                    types.get(i-1).name += ", "+types.get(i).name;
                    types.remove(i);
                }
            }
        }

        if(Objects.equals(types.get(types.size() - 2).uri, types.get(types.size() - 1).uri)){
            types.get(types.size()-2).name += ", "+types.get(types.size()-1).name;
            types.remove(types.size()-1);
        }
        return types;
    }
}
