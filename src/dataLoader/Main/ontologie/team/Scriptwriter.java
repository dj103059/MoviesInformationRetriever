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
public class Scriptwriter {

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
            "?uriWriter rdfs:label ?name." +
            " } ";
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

    public Scriptwriter (String name){
        this.name = name;
    }

    /**
     * Contruct a list of scriptwriters
     * @return list of scriptwriters
     */
    public static ArrayList<Scriptwriter> constructListOfscriptwritersForMovie(String urimovie, Query q, QueryExecution qr, ResultSet r,Model m){
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
            scriptwritertemp =  new Scriptwriter(urimovie,name.getString().replace(" (Writer)",""));
            scriptwriters.add(scriptwritertemp);

        }
        return scriptwriters;
    }

    /**
     * construct list of type from dataset
     *
     * @param q
     * @param qr
     * @param r
     * @return list of type
     */
    public static ArrayList<Scriptwriter> constructListOfWriter(Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestforinstance;
        ArrayList<Scriptwriter> scriptwriters = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            scriptwriters.add(new Scriptwriter(name.getString().replace(" (Writer)","")));
        }
        return scriptwriters;
    }

    // ajoute la liste d'instance de type dans l'ontologie
    public void addWriterToOntologie(Model m) {
        int randomNum = ThreadLocalRandom.current().nextInt(0,101);
        String prefixemo = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
        String prefixerdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String prefixerdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

        try {
            Resource resourceWriter = m.createResource(prefixemo + this.name);
            //type
            m.add(resourceWriter, RDF.type, ResourceFactory.createResource(prefixemo + "Scriptwriter"));
            //add title
            Property label = m.createProperty(prefixerdfs + "label");
            resourceWriter.addProperty(label, this.name);
            //add weight
            Property weight = m.createProperty(prefixerdfs + "seeAlso");
            resourceWriter.addProperty(weight, ""+randomNum);

        } catch (Exception e) {

        }

        /*try {
            m.write(new FileOutputStream("/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie1.owl"), "RDF/XML");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }

}
