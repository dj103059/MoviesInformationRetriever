package akinator.weightManagement;

import java.util.ArrayList;

//import
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

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

	private String value;

	private String label_leaf;

	private String weight_leaf;

	private ArrayList<String> Value_Label_Weight_Triplet = new ArrayList<String>();


	/*****CONSTRUCTORS*****/
	public WeightManagement() {
		this.property = "";
		this.label_masterBanch = "";
		this.weight_masterBanch = "";
		//add the triplet to the list
		addTriplet_MasterBranch(this.property, this.label_masterBanch, this.weight_masterBanch);
		
		this.value ="";
		this.label_leaf = "";
		this.weight_leaf = "";
		//add the triplet to the list
		addTriplet_leaf(this.value, this.label_leaf, this.weight_leaf);

	}

	public WeightManagement(String property, String label_masterBanch, String poids_masterBanch) {
		this.property = property;
		this.label_masterBanch = label_masterBanch;
		this.weight_masterBanch = poids_masterBanch;
		//add the triplet to the list
		addTriplet_MasterBranch(this.property, this.label_masterBanch, this.weight_masterBanch);
		
		this.value ="";
		this.label_leaf = "";
		this.weight_leaf = "";
		//add the triplet to the list
		addTriplet_leaf(this.value, this.label_leaf, this.weight_leaf);

	}

	public WeightManagement(String property, String label_masterBanch, String weight_masterBanch, String value,
			String label_leaf, String weight_leaf) {
		this.property = property;
		this.label_masterBanch = label_masterBanch;
		this.weight_masterBanch = weight_masterBanch;
		this.value = value;
		this.label_leaf = label_leaf;
		this.weight_leaf = weight_leaf;
	}

	/*****GETTERS AND SETTERS*****/
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getPrefixeMovie() {
		return prefixeMovie;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel_leaf() {
		return label_leaf;
	}

	public void setLabel_leaf(String label_leaf) {
		this.label_leaf = label_leaf;
	}

	public String getWeight_leaf() {
		return weight_leaf;
	}

	public void setWeight_leaf(String weight_leaf) {
		this.weight_leaf = weight_leaf;
	}

	public ArrayList<String> getValue_Label_Weight_Triplet() {
		return Value_Label_Weight_Triplet;
	}

	public void setValue_Label_Weight_Triplet(ArrayList<String> value_Label_Weight_Triplet) {
		Value_Label_Weight_Triplet = value_Label_Weight_Triplet;
	}

	/*****METHODS*****/

	private void addTriplet_MasterBranch (String property, String label_masterBanch, String weight_masterBanch){
		//add the triplet to the list
		this.Property_Label_Weight_Triplet.add(property);
		this.Property_Label_Weight_Triplet.add(label_masterBanch);
		this.Property_Label_Weight_Triplet.add(weight_masterBanch);
	}

	private void addTriplet_leaf (String value, String label_leaf, String weight_leaf){
		//add the triplet to the list
		this.Value_Label_Weight_Triplet.add(value);
		this.Value_Label_Weight_Triplet.add(label_leaf);
		this.Value_Label_Weight_Triplet.add(weight_leaf);
	}

	private void MasterBranch_MaxWeight(){
		final String querySring = this.prefix+"Select distinct ?uri_property ?label ?poids where {?uri_property rdfs:label ?label. ?uri_property rdfs:isDefinedBy ?poids . } order by desc (?poids) LIMIT 1";
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
				Resource property = soln.getResource("uri_property");
				Literal label_masterBanch = soln.getLiteral("label");
				Literal poids_masterBanch  = soln.getLiteral("poids");
				this.property = property.toString();
				this.label_masterBanch = label_masterBanch.getString();
				this.weight_masterBanch = poids_masterBanch.getString();
				//add the triplet to the list
				addTriplet_MasterBranch(this.property, this.label_masterBanch, this.weight_masterBanch);
			}
		}
		finally{
			qexec.close();
		}

	}

	private void leaf_MaxWeight(String Label_MasterBranch){
		final String querySring = this.prefix+"Select distinct ?uri_value ?main_branch ?label ?poids where {?uri_value owl:versionInfo ?main_branch. ?uri_value owl:versionInfo "+Label_MasterBranch+". ?uri_value rdfs:label ?label ?uri_value rdfs:seeAlso ?poids . }order by desc (?poids) LIMIT 1";
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
				Resource value = soln.getResource("uri_value");
				Literal label_leaf = soln.getLiteral("label");
				Literal weight_leaf  = soln.getLiteral("poids");
				this.value = value.toString();
				this.label_leaf = label_leaf.getString();
				this.weight_leaf = weight_leaf.getString();
				//add the triplet to the list
				addTriplet_leaf(this.value, this.label_leaf, this.weight_leaf);
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
	
	public ArrayList<String> getLeaf_MaxWeightTriplet(String Label_MasterBranch){
		leaf_MaxWeight(Label_MasterBranch);
		return this.Value_Label_Weight_Triplet;
	}

	public String getMasterBranch_MaxWeight_Property(){
		MasterBranch_MaxWeight(); 
		StringBuffer foundProperty = new StringBuffer(this.property);
		try{
			foundProperty = foundProperty.replace(0, prefixeMovie.length(), "");//remove the prefix to get only the property
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


}


