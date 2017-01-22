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
public class Scriptwriter {
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
            "?uriWriter rdfs:label ?name."+
            "?uri mo:producer ?uriProducer." +
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
    public static ArrayList<Scriptwriter> constructListOfscriptwriters(Query q, QueryExecution qr, ResultSet r){
        //construit la liste de scriptwriters
        ArrayList<Scriptwriter> scriptwriters = new ArrayList<Scriptwriter>();
        q = create(request);
        qr = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", q);
        r = qr.execSelect();
        Scriptwriter scriptwritertemp;
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            Resource uri = (Resource) binding.get("uri");
            scriptwritertemp =  new Scriptwriter(uri.getURI(),name.getString());
            scriptwriters.add(scriptwritertemp);

        }
        return scriptwriters;
    }

    public static ArrayList<Scriptwriter> refactorListofScriptwriter(ArrayList<Scriptwriter> scriptwriters){
        for (int i = 0; i< scriptwriters.size();i++) {
            if(i != 0){
                while(Objects.equals(scriptwriters.get(i).uri, scriptwriters.get(i - 1).uri) && i < (scriptwriters.size()-1)){
                    scriptwriters.get(i-1).name += ", "+scriptwriters.get(i).name;
                    scriptwriters.remove(i);
                }
            }
        }

        if(Objects.equals(scriptwriters.get(scriptwriters.size() - 2).uri, scriptwriters.get(scriptwriters.size() - 1).uri)){
            scriptwriters.get(scriptwriters.size()-2).name += ", "+scriptwriters.get(scriptwriters.size()-1).name;
            scriptwriters.remove(scriptwriters.size()-1);
        }
        return scriptwriters;
    }
}
