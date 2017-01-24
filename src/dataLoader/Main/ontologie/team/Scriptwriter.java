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
public class Scriptwriter {
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?name WHERE"+
            " { <";
    public static String requestpart2 =
            "> mo:writer ?uriWriter." +
            "?uriWriter rdfs:label ?name."+
            " }  ";
    public String uri ;
    public String name;

    public Scriptwriter (String uri ,String name){
        this.uri = uri;
        this.name = name;
    }

    /**
     * Contruct a list of scriptwriters
     * @return list of scriptwriters
     */
    public static ArrayList<Scriptwriter> constructListOfscriptwriters(String urimovie, Query q, QueryExecution qr, ResultSet r,Model m){
        String finalRequest = requestpart1 + urimovie + requestpart2;
        //construit la liste de scriptwriters
        ArrayList<Scriptwriter> scriptwriters = new ArrayList<Scriptwriter>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        Scriptwriter scriptwritertemp;
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            scriptwritertemp =  new Scriptwriter(urimovie,name.getString());
            scriptwriters.add(scriptwritertemp);

        }
        return scriptwriters;
    }

}
