package dataLoader.Main.ontologie;


import org.apache.jena.base.Sys;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Type {
    //permet de récupérer la liste des instances de type
    public static String requestforinstance = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE" +
            " { ?uri  mo:genre ?urigenre. " +
            "?urigenre rdfs:label ?name." +
            " } ";

    //permet de récupérer les types pour un film donné
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE" +
            " { <";
    public static String requestpart2 =
            "> mo:genre ?urigenre." +
                    "?urigenre rdfs:label ?name." +
                    " }  ";
    public String uri;
    public String name;

    public Type(String uri, String label) {
        this.uri = uri;
        this.name = label;
    }

    public Type(String label) {
        this.name = label;
    }

    /**
     * construct list of type for a movie
     *
     * @param q
     * @param qr
     * @param r
     * @return list of type for each film
     */
    public static ArrayList<Type> constructListOfTypeForMovie(String urimovie, Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestpart1 + urimovie + requestpart2;
        ArrayList<Type> types = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            types.add(new Type(urimovie, name.getString().replace("(Film Genre)","")));
        }
        return types;
    }


    /**
     * construct list of type from dataset
     *
     * @param q
     * @param qr
     * @param r
     * @return list of type
     */
    public static ArrayList<Type> constructListOfType(Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestforinstance;
        ArrayList<Type> types = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            types.add(new Type(name.getString().replace("(Film Genre)","")));
        }
        return types;
    }


    // ajoute la liste d'instance de type dans l'ontologie
    public void addTypeToOntologie(Model m) {
        String prefixemo = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
        String prefixerdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String prefixerdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

        try {
            Resource resourceType = m.createResource(prefixemo + this.name);
            //type
            Property type = m.createProperty(prefixerdf + "type");
            resourceType.addProperty(type, prefixemo + "Type");
            //add title
            Property label = m.createProperty(prefixerdfs + "label");
            resourceType.addProperty(label, this.name);

        } catch (Exception e) {

        }



    }
}
