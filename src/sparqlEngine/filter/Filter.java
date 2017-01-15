package sparqlEngine.filter;

import com.oracle.webservices.internal.api.message.PropertySet;

/**
 * Class for the representation of the filter
 *
 * Created by titanium on 10/01/2017.
 */
public class Filter {

    private String filter;

    public Filter(String filter){
        this.filter = filter;
    }

    /**
     * build  filter with the good property and the good value
     *  in function of the answer
     * @param property
     * @param value
     * @param answer
     * @return
     */
    public void constructFilter(String property, String value, boolean answer){
        //TODO : works with movie propertie, but not with rdfs property
        if(answer){
            setFilter("?uri mo:"+ property +" mo:" + value + ".\n");
        }else{
            setFilter("filter not exists {?uri mo:"+ property +" mo:" + value + "}.\n");
        }

    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
