package sparqlEngine.request;

import sparqlEngine.filter.Filter;

/**
 * Class for the request representation
 *
 * Created by titanium on 10/01/2017.
 */
public class Request {


    private String prefixeMovie = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n";
    private String mainQuery = "Select distinct ?label ?comment  where { \n"+"?uri rdfs:label ?label.\n" +
            "  ?uri rdfs:comment ?comment.\n"+"}";


    public Request(){
        setMainQuery(prefixeMovie+mainQuery);
    }

    /**
     * execute Querry to get the result
     * @return
     */
    public String getResult(){
        //TODO: if count >1 return NONE else return result
        return "none";
    }

    /**
     *  add filter to main querry
     * @param filter
     * @return
     */
    private void addFilter( Filter filter){
        removeEndQuery();
        setMainQuery(getMainQuery()+filter.getFilter()+"}");
    }

    /**
     * Remove the endQuerry to put another line
     */
    private void removeEndQuery(){
        setMainQuery(getMainQuery().substring(0,getMainQuery().length()-1));
    }

    public String getMainQuery() {
        return mainQuery;
    }

    public void setMainQuery(String mainQuery) {
        this.mainQuery = mainQuery;
    }
}
