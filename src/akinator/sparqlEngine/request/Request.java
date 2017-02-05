package akinator.sparqlEngine.request;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;

import akinator.init.Initialisation;
import akinator.sparqlEngine.filter.Filter;

/**
 * Class for the request representation
 *
 * Created by titanium on 10/01/2017.
 */
public class Request {

	/******ATTRIBUTES******/

    private final String prefixeMovie = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n"
    		+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
    private String mainQuery = "Select distinct ?label ?comment  where { \n"+"?uri rdfs:label ?label.\n" +
            "  OPTIONAL { ?uri rdfs:comment ?comment. } \n"+"}";



    private String countQuery =  "Select distinct (count(?uri ) as ?count)   where { \n"
            + "?uri rdfs:label ?label.\n" +
            "  OPTIONAL { ?uri rdfs:comment ?comment. } \n"+"}";
    public String title;
    public String comment;
    public  String result = "<html>Your film is: ";
    

    /******CONSTRUCTORS******/
    
    public Request(){
        setMainQuery(prefixeMovie+mainQuery);
        setCountQuery(prefixeMovie+countQuery);
    }
    
    /******GETTERS AND SETTERS******/
    
    public String getMainQuery() {
        return mainQuery;
    }

    public void setMainQuery(String mainQuery) {
        this.mainQuery = mainQuery;
    }
    
    /******CLASS METHODS******/

    /**
     * execute Querry to get the result
     * @return
     */
    public String getResult(){
    	String result = "OK";
        //System.out.print(queryString);
        Query query = QueryFactory.create(this.mainQuery);
        Query querycount  = QueryFactory.create(this.countQuery);
        System.out.println(this.countQuery);
        QueryExecution qexecCount = QueryExecutionFactory.create(querycount, Initialisation.getModel());
        QueryExecution qexec = QueryExecutionFactory.create(query, Initialisation.getModel());
        try{
        	org.apache.jena.query.ResultSet results = qexec.execSelect();
            //////////////////////////
            org.apache.jena.query.ResultSet resultsCount = qexecCount.execSelect();
        	int compteur = 0;
            while (resultsCount.hasNext()){
                QuerySolution soln = resultsCount.nextSolution();
                compteur  = soln.getLiteral("count").getInt();
            }
            System.out.println("Compteur = "+compteur );
            if(compteur>1){
                return "NONE";
            }
            else if (compteur==0){
                return "NF";
            }
            /////////////////////////
        	while (results.hasNext()){
        		compteur++;
        		QuerySolution soln = results.nextSolution();
        		Literal title = soln.getLiteral("label");
        		Literal comment = soln.getLiteral("comment");
        		this.title = title.getString().replace("-"," ");
        		this.comment = comment.getString();
        	}

        }
        finally{
        	qexec.close();
        }
        
        return result;
    }

    /**
     *  add filter to main querry
     * @param filter
     * @return
     */
    public void addFilter(Filter filter){
        removeEndQuery();
        setMainQuery(getMainQuery()+filter.getFilter()+"}");
        setCountQuery(getCountQuery()+filter.getFilter()+"}");
    }

    /**
     * Remove the endQuerry to put another line
     */
    private void removeEndQuery(){
        setMainQuery(getMainQuery().substring(0,getMainQuery().length()-1));
        setCountQuery(getCountQuery().substring(0,getCountQuery().length()-1));
    }

    public void setResult(){
        result = result + " " + title +"<br/>" + comment +"</html>";
    }

    public String getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(String countQuery) {
        this.countQuery = countQuery;
    }
}
