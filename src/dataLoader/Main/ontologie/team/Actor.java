package dataLoader.Main.ontologie.team;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Actor {
    public String uri ;

    public static String requestforinstance = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE" +
            " { ?uri  mo:producer ?uriProducer. " +
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?country." +
            "?uri mo:genre ?genre." +
            "?uri mo:writer ?uriWriter." +
            "?uri mo:actor ?uriactor." +
            "?uriactor rdfs:label ?name." +
            " } ";
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

    public Actor(String label){
        this.name = label;
    }


    /**
     * construct list of actor
     * @param q
     * @param qr
     * @param r
     * @return list of actor for each film
     */
    public static ArrayList<Actor> constructListOfActorForMovie(String urimovie, Query q, QueryExecution qr, ResultSet r, Model m){
        String finalRequest = requestpart1 + urimovie + requestpart2;
        ArrayList<Actor> actors = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            actors.add(new Actor(urimovie,name.getString().replace(" (Actor)","")));
        }
        return actors;
    }



    public static ArrayList<Actor> constructListOfActor(Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestforinstance;
        ArrayList<Actor> actors = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            actors.add(new Actor(name.getString().replace(" (Actor)","")));
        }
        return actors;
    }

    // ajoute la liste d'instance de type dans l'ontologie
    public void addActorToOntologie(Model m) {
        int randomNum = ThreadLocalRandom.current().nextInt(0,101);
        String prefixemo = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
        String prefixerdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String prefixerdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

        try {
            Resource resourceActor = m.createResource(prefixemo + this.name);
            //type
            m.add(resourceActor, RDF.type, ResourceFactory.createResource(prefixemo + "Actor"));
            //add title
            Property label = m.createProperty(prefixerdfs + "label");
            resourceActor.addProperty(label, this.name);
            //add weight
            Property weight = m.createProperty(prefixerdfs + "seeAlso");
            resourceActor.addProperty(weight, ""+randomNum);

        } catch (Exception e) {

        }

        /*try {
            m.write(new FileOutputStream("/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie1.owl"), "RDF/XML");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }



}

