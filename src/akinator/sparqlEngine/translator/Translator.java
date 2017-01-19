package akinator.sparqlEngine.translator;

import fr.inria.acacia.corese.api.IDatatype;
import fr.inria.acacia.corese.exceptions.EngineException;
import fr.inria.edelweiss.kgram.core.Mapping;
import fr.inria.edelweiss.kgram.core.Mappings;
import fr.inria.edelweiss.kgraph.query.QueryProcess;
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
	
    private  String querryLabel = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n";
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
                "Select ?label where {\n mo:"+
                obj + "rdfs:label ?label.}";

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
     * realise the SPARQL request for a querry based on a label
     * @param querry
     * @return
     */
    private String getResultFromMap(String querry){
        String result = "";

        QueryProcess exec = QueryProcess.create(Initialisation.graph);
        try {
            Mappings map = exec.query(querryLabel);
            for (Mapping m : map) {
                IDatatype dtlabel = (IDatatype) map.getValue("?label");
                IDatatype dtcomment = (IDatatype) map.getValue("?labelcomment");
                result =  dtlabel.stringValue();
                result =  dtcomment.stringValue();
            }

        } catch (EngineException e) {
            e.printStackTrace();
        }
        return result;
        //return result+"<br>"+resultcoment;

    }

   
}
