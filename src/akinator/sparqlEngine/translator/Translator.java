package akinator.sparqlEngine.translator;


import java.sql.ResultSet;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.util.FileManager;

import akinator.Main;
import akinator.init.Initialisation;
import akinator.init.StoredComponent;

/* 1er boucle
 Translator t =  new Translator().translate(storedcomponent);
 t.getAskMovie();
 afficher question
 Filter f = new filter();
 f.constructfilter(storedComponent)
  Request r  = new Request();
  r.addFilter(f).
 Request r  = new Request().addFilter((new Filter().constructfilter(storedComponent)))
 r.getResult ???????


 deuxiéme boucle
 t.translate(storedcomponent)
 t.getAskMovie();
 r.addFilter((new Filter().constructfilter(storedComponent))
r.getResult ??????? */

/**
 * class to translate from owl to natural language
 * Created by titanium on 10/01/2017.
 */
public class Translator {
	
	/******ATTRIBUTES******/
	
    private  String querryLabel = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n"
    		+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
    private  String askMovie = "Your film ";
    private  String result = "<html> Your film is ";

    /******CONSTRUCTORS******/

    /**
     * constructor
     */
    public Translator(){

    }
    
    /******GETTERS AND SETTERS******/
    
    /**
     * getter and setter
     */


    public String getAskMovie() {
        return askMovie;
    }

    public void setAskMovie(String askMovie) {
        this.askMovie = askMovie;
    }

    public String getResult() {
        return result;
    }
    
    
    /******CLASS METHODS******/ 

    /**
     * use the stored component to create the question
     * @param storedComponent
     * @return askMovie
     */
    public String translate(StoredComponent storedComponent){

        if(storedComponent.getFormat() == "rdfs"){
            this.askMovie = this.askMovie + getLabel(storedComponent.getProperty()) +" " + storedComponent.getValue()  +" ?";
        }else{
            this.askMovie = this.askMovie + getLabel(storedComponent.getProperty()) +" " + getLabel(storedComponent.getValue()) +" ?";
        }
        
        return getAskMovie();


    }

    /**
     * get label of the property value
     * @param obj
     * @return
     */
    public String getLabel(String obj){
        String propertyLabel =  "";

        //create request
        String querryLabel = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n" +
        		"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+" Select ?label where {\n mo:"+
                obj + " rdfs:label ?label.}";

        propertyLabel = getResultFromMap(querryLabel);

        return propertyLabel;

    }

    /**
     * set the final sentence
     * @param res
     */
    public void setResult(String res){
        result = result + " " + res +"</html>";
    }

    /**
     * Realize the SPARQL request for a query based on a label
     * @param query
     * @return
     */
    private String getResultFromMap(String queryString){
        String result = "";
        //System.out.print(queryString);
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, Initialisation.getModel());
        try{
        	org.apache.jena.query.ResultSet results = qexec.execSelect();
        	while (results.hasNext()){
        		QuerySolution soln = results.nextSolution();
        		Literal name = soln.getLiteral("label");
        		result = name.toString();
        		//System.out.println(result);
        	}
        }
        finally{
        	qexec.close();
        }
        
        return result;
        //return result+"<br>"+resultcoment;

    }

   
}
