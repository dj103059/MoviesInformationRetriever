package akinator.weightManagement;

import java.util.ArrayList;

//import
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;

import akinator.init.Initialisation;

public class WeightManagement {

	/******ATTRIBUTS******/
	
	   private final String prefixeMovie = "http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#";
	   
	   private final String prefix = "prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>\n"
	    		+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> ";
	   
	   private String property;
	   
	   private String label_masterBanch;
	   
	   private String weight_masterBanch;
	   
	   private ArrayList<String> Property_Label_Weight_Triplet = new ArrayList<String>();
	   
	
	/*****CONSTRUCTORS*****/
	   public WeightManagement() {
			this.property = "";
			this.label_masterBanch = "";
			this.weight_masterBanch = "";
			//add the triplet to the list
			addTriplet(this.property, this.label_masterBanch, this.weight_masterBanch);
			
		}
	   
	   public WeightManagement(String property, String label_masterBanch, String poids_masterBanch) {
			this.property = property;
			this.label_masterBanch = label_masterBanch;
			this.weight_masterBanch = poids_masterBanch;
			//add the triplet to the list
			addTriplet(this.property, this.label_masterBanch, this.weight_masterBanch);
			
		}
	
	/*****GETTERS AND SETTERS*****/

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLabel_masterBanch() {
		return label_masterBanch;
	}

	public void setLabel_masterBanch(String label_masterBanch) {
		this.label_masterBanch = label_masterBanch;
	}

	public String getWeight_masterBanch() {
		return weight_masterBanch;
	}

	public void setWeight_masterBanch(String weight_masterBanch) {
		this.weight_masterBanch = weight_masterBanch;
	}

	public ArrayList<String> getProperty_Label_Weight_Triplet() {
		return Property_Label_Weight_Triplet;
	}

	public void setProperty_Label_Weight_Triplet(ArrayList<String> property_Label_Weight_Triplet) {
		Property_Label_Weight_Triplet = property_Label_Weight_Triplet;
	}

	public String getPrefixeMovie() {
		return prefixeMovie;
	}

	/*****METHODS*****/
	
	private void addTriplet (String property, String label_masterBanch, String weight_masterBanch){
		//add the triplet to the list
		this.Property_Label_Weight_Triplet.add(property);
		this.Property_Label_Weight_Triplet.add(label_masterBanch);
		this.Property_Label_Weight_Triplet.add(weight_masterBanch);
	}
	
	 private void MasterBranch_MaxWeight(){
		 final String querySring = this.prefix+"Select distinct ?uri_property ?label ?poids  where {?uri_property rdfs:label ?label ?uri_property rdfs:isDefinedBy ?poids . } order by desc (?poids) LIMIT 1";
	        //System.out.print(queryString);
	        Query query = QueryFactory.create(querySring);
	        //System.out.println(this.mainQuery);
	        QueryExecution qexec = QueryExecutionFactory.create(query, Initialisation.getModel());
	        try{
	        	org.apache.jena.query.ResultSet results = qexec.execSelect();
	        	//System.out.println(results.getRowNumber());
	        	
	        	int compteur = 0;
	        	while (results.hasNext()){
	        		compteur++;
	        		QuerySolution soln = results.nextSolution();
	        		Literal property = soln.getLiteral("uri_property");
	        		Literal label_masterBanch = soln.getLiteral("label");
	        		Literal poids_masterBanch  = soln.getLiteral("poids");
	        		this.property = property.getString();
	        		this.label_masterBanch = label_masterBanch.getString();
	        		this.weight_masterBanch = poids_masterBanch.getString();
	        		//add the triplet to the list
	    			addTriplet(this.property, this.label_masterBanch, this.weight_masterBanch);
	        	 }
	        }
		        finally{
		        	qexec.close();
		        }
	        		
	        	}
	
	 public ArrayList<String> getMasterBranch_MaxWeightTriplet(){
		 MasterBranch_MaxWeight();
	     return this.Property_Label_Weight_Triplet;
	    }
	 
	 public String getMasterBranch_MaxWeight_Property(){
		 MasterBranch_MaxWeight(); 
		 StringBuffer foundProperty = new StringBuffer(this.property);
		 try{
		 foundProperty = foundProperty.replace(0, prefixeMovie.length()-1, "");//remove the prefix to get only the property
		 }
		 catch(StringIndexOutOfBoundsException e){
			 System.out.print("StringIndexOutOfBoundsException Error. Cause: "+e.getCause()+" Message: "+e.getMessage());
			 e.getStackTrace();
		 }
		 
		 return property = foundProperty.toString();
		 
		 }
	 
	 public String getMasterBranch_MaxWeight_Label(){
		 MasterBranch_MaxWeight(); 
		 return this.label_masterBanch;
		 }
	 
	 public String getMasterBranch_MaxWeight_Weight(){
		 MasterBranch_MaxWeight(); 
		 return this.weight_masterBanch;
		 }

	 public static void main(String[] args) {
		 WeightManagement wm = new WeightManagement();
		 System.out.println(wm.getMasterBranch_MaxWeight_Property());
		 System.out.println(wm.getMasterBranch_MaxWeight_Label());
		 System.out.println(wm.getMasterBranch_MaxWeight_Weight());
			
		}
	 
}


