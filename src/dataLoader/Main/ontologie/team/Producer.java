package dataLoader.Main.ontologie.team;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 19/01/2017.
 */
public class Producer {

    //permet de récupérer la liste des instances de producteur
    public static String requestforinstance = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE" +
            " { ?uri  mo:producer ?uriProducer. " +
            "?uriProducer rdfs:label ?name." +
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?country." +
            "?uri mo:genre ?genre." +
            "?uri mo:writer ?uriWriter." +
            " } ";

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

    public Producer(String name){
        this.name = name;
    }



    /**
     * Contruct a list of producers
     * @return list of producers
     */
    public static ArrayList<Producer> constructListOfProducerForMovie(String movieuri, Query q, QueryExecution qr, ResultSet r,Model m){
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
            producertemp =  new Producer(movieuri,name.getString().replace("(Producer)","").replace(" ","-").replace("'",""));
            producers.add(producertemp);

        }
        return producers;
    }

    public static ArrayList<Producer> constructListOfProducer(Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestforinstance;
        ArrayList<Producer> producers = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            producers.add(new Producer(name.getString().replace("(Producer)","").replace(" ","-").replace("'","").replace("Ã\u00AD","i").replace("Ã³","o")));
        }
        return producers;
    }

    // ajoute la liste d'instance de type dans l'ontologie
    public void addProducerToOntologie(Model m) {
        int randomNum = ThreadLocalRandom.current().nextInt(0,101);
        String prefixemo = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
        String prefixerdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String prefixerdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

        try {
            Resource resourceProducer = m.createResource(prefixemo + this.name);
            //type
            m.add(resourceProducer, RDF.type, ResourceFactory.createResource(prefixemo + "Producer"));
            //add title
            Property label = m.createProperty(prefixerdfs + "label");
            resourceProducer.addProperty(label, this.name.replace("-"," "));
            //add weight
            Property weight = m.createProperty(prefixerdfs + "seeAlso");
            resourceProducer.addProperty(weight, ""+randomNum);

        } catch (Exception e) {

        }

        /*try {
            m.write(new FileOutputStream("/Users/titanium/Desktop/MoviesInformationRetriever/File/RDFS_file/OntologieMovie1.owl"), "RDF/XML");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }


}
