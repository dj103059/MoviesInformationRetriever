package sparqlEngine.request;

import sparqlEngine.Filter;

/**
 * Class for the request representation
 *
 * Created by titanium on 10/01/2017.
 */
public class Request {
    private String mainQuery = "Select distinct ?label ?description where {";
    private String endQuery = "}";

    public Request(){

    }

    /**
     * execute Querry to get the result
     * @param querry
     * @return
     */
    public String getResult(String querry){
        //TODO: if count >1 return NONE else return result
        return "none";
    }

    /**
     *  add filter to a querry
     * @param querry
     * @param filter
     * @return
     */
    private String addFilter(String querry, Filter filter){
        //TODO: return querry with filer
        return "NON";
    }
}
