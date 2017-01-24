package akinator;

import akinator.init.Initialisation;
import akinator.init.StoredComponent;
import akinator.sparqlEngine.filter.Filter;
import akinator.sparqlEngine.request.Request;
import akinator.sparqlEngine.translator.Translator;

public class Main {

    public static void main(String[] args) {
    	org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning
        Initialisation initialisation= new Initialisation(); //Load the file and the ontology (PB: there are issue on the load method)
        StoredComponent storedcomponent = new StoredComponent("Playby", "Carrie Fisher", ""); //instanciate a StoredComponent : specified the property and value. The format is "rdfs" if the value have a primitive type.
        Translator t =  new Translator(); //instanciate a translator to create the question in a natural language
        String question = t.translate(storedcomponent); // use the storedcomponent to create the question in a natural language (PB: nullPointeurException, is the problem due to the issue in the load method ?)
        //TODO: afficher question dans la fenetre graphique
        Filter f = new Filter(); //instanciate a new filter to construct the filter of the query
        f.constructFilter(storedcomponent, true);// Construct the filter of the query if we suppose that the user respond yes to the question.
        Request r  = new Request(); //create the request with the filter to know if we have found the film or if we have several films that fit to this filter.
        r.addFilter(f);//make the request with the filter f that we have construct
        //String mainQuery = r.getMainQuery();// we get the main query
//        r.getResult ??????? //TODO: this method has to be implemented 
    }
}
