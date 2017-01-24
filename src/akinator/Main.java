package akinator;

import com.github.andrewoma.dexx.collection.ArrayList;

import akinator.graphic_design.Main_window;
import akinator.init.Initialisation;
import akinator.init.StoredComponent;
import akinator.sparqlEngine.filter.Filter;
import akinator.sparqlEngine.request.Request;
import akinator.sparqlEngine.translator.Translator;

public class Main {
	public static void main(String[] args) {
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);//Avoid log4j warning

		Main_window window = new Main_window();
		//////////////////////////////////////
		 window.translateAndShow();
		///////////////////////////////
		
		//main.evenentListerner()
		/*
		 * boolean = answer
		 *main.MainEngine()
		 * f.constructFilter(listStoredcomponent.get(i), listResponse.get(i));
			r.addFilter(f);
			System.out.println(r.getResult());
			si c'est none => window.translateandshow(listStoredcomponent.get(i+1))
			sinon main.showResult
				System.out.println("final :"+r.getResult());
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
//		System.out.println(listResponse.get(i));
//		//TODO: afficher question dans la fenetre graphique
//		Main_window window = new Main_window(question);
////		boolean reponse = window.isReponse();
//		//System.out.println(reponse);
//		Filter f = new Filter(); //instanciate a new filter to construct the filter of the query
//		f.constructFilter(listStoredcomponent.get(i), listResponse.get(i));// Construct the filter of the query if we suppose that the user respond yes to the question.
//		Request r  = new Request(); //create the request with the filter to know if we have found the film or if we have several films that fit to this filter.
//		r.addFilter(f);//make the request with the filter f that we have construct
//		//String mainQuery = r.getMainQuery();// we get the main query
//		//System.out.println(r.getMainQuery());
//		System.out.println(r.getResult());  //Return NONE if there is several result to the query. Else, return the final result film
//		
//		while(r.getResult()=="NONE"){
//			i++;
//			question = t.translate(listStoredcomponent.get(i));
//			System.out.println(question);
//			window.setQuestion(question);
//			System.out.println(listResponse.get(i));
//			f.constructFilter(listStoredcomponent.get(i), listResponse.get(i));
//			r.addFilter(f);
//			System.out.println(r.getResult());
//		}
//		System.out.println("final :"+r.getResult());
	}
}
