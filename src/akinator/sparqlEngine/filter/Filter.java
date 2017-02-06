package akinator.sparqlEngine.filter;

import akinator.init.StoredComponent;

/**
 * Class for the representation of the filter
 *
 * Created by titanium on 10/01/2017.
 */
public class Filter {
	
	/******ATTRIBUTES******/

    private String filter;

    /******CONSTRUCTORS******/
    
    public Filter(
    ){

    }
    
    /******GETTERS AND SETTERS******/
    
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    
    /******CLASS METHODS******/

    /**
     * build  filter with the good property and the good value
     *  in function of the answer
     * @param storedComponent
     * @param answer
     * @return
     */
    public void constructFilter(StoredComponent storedComponent, boolean answer){

        System.out.println("storedComponent.getProperty() " + storedComponent.getProperty());
        // to know if ti's our format or rdfs format

        String filtervalue = "";

        if(storedComponent.getFormat() == "rdfs"){
            filtervalue = " \"" + storedComponent.getValue() +"NONE\"" ;
        }else{
            filtervalue = " mo:" + storedComponent.getValue() ;
        }

        if(answer){
            setFilter("?uri mo:"+ storedComponent.getProperty() + filtervalue + ".\n");
        }else{
            setFilter("filter not exists {?uri mo:"+ storedComponent.getProperty() + filtervalue + "}.\n");
        }

    }


}
