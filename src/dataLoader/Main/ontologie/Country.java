package dataLoader.Main.ontologie;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.jena.query.QueryFactory.create;

/**
 * Created by titanium on 06/02/2017.
 */
public class Country {

    public static String requestforinstance = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT  ?name WHERE" +
            " { ?uri  mo:producer ?uriProducer. " +
            "?uri a mo:film. "+
            "?uri mo:initial_release_date ?date."+
            "?uri mo:runtime ?duration."+
            "?uri mo:country ?countrytemp."+
            "?countrytemp rdfs:label ?name." +
            "?uri mo:genre ?genre." +
            "?uri mo:writer ?uriWriter." +
            " } ";
    public static String requestpart1 = "prefix mo:  <http://data.linkedmdb.org/resource/movie/>" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT DISTINCT ?name WHERE"+
            " { <";
    public static String requestpart2 =
            "> mo:country ?countrytemp."+
                    "?countrytemp rdfs:label ?name." +
                    " }  ";
    public String uri ;
    public String name;

    public Country (String uri ,String name){
        this.uri = uri;
        this.name = name;
    }

    public Country (String name){
        this.name = name;
    }

    /**
     * Contruct a list of Country
     * @return list of Country
     */
    public static ArrayList<Country> constructListOfCountrysForMovie(String urimovie, Query q, QueryExecution qr, ResultSet r, Model m){
        String finalRequest = requestpart1 + urimovie + requestpart2;
        //construit la liste de scriptwriters
        ArrayList<Country> countries = new ArrayList<Country>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        Country scriptwritertemp;
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            scriptwritertemp =  new Country(urimovie,name.getString().replace(" (Country)","").replace(" ","-"));
            countries.add(scriptwritertemp);

        }
        return countries;
    }

    /**
     * construct list of type from dataset
     *
     * @param q
     * @param qr
     * @param r
     * @return list of type
     */
    public static ArrayList<Country> constructListOfCountry(Query q, QueryExecution qr, ResultSet r, Model m) {
        String finalRequest = requestforinstance;
        ArrayList<Country> countries = new ArrayList<>();
        q = create(finalRequest);
        qr = QueryExecutionFactory.create(q, m);
        r = qr.execSelect();
        while (r.hasNext()) {
            QuerySolution binding = r.nextSolution();
            Literal name = binding.getLiteral("name");
            countries.add(new Country(name.getString().replace(" (Country)","").replace(" ","-")));
        }
        return countries;
    }

    // ajoute la liste d'instance de type dans l'ontologie
    public void addCountryToOntologie(Model m) {
        int randomNum = ThreadLocalRandom.current().nextInt(0,101);
        String prefixemo = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
        String prefixerdfs = "http://www.w3.org/2000/01/rdf-schema#";

        try {
            Resource resourcecountry = m.createResource(prefixemo + this.name);
            //type
            m.add(resourcecountry, RDF.type, ResourceFactory.createResource(prefixemo + "Country"));
            //add title
            Property label = m.createProperty(prefixerdfs + "label");
            resourcecountry.addProperty(label, this.name.replace("-"," "));
            //add weight
            Property weight = m.createProperty(prefixerdfs + "seeAlso");
            resourcecountry.addProperty(weight, ""+randomNum);

        } catch (Exception e) {

        }


    }
}
