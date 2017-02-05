package akinator.init;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

/**
 * class for load file and start process
 *
 * Created by titanium on 10/01/2017.
 */
public class Initialisation {
	
	/******ATTRIBUTES******/

    private static Model model;

    


	/******CONSTRUCTORS******/
    
    public Initialisation(){
        loadfile();
    }
    
    /******GETTERS******/
    
    public static Model getModel() {
		return model;
	}
    
    /******CLASS METHODS******/
    
    /**
     * load file for Graph
     */
    public void loadfile (){
    	
    	  final String inputFileName  = "File/RDFS_file/OntologieMovie1.owl";

    	// créer un modèle vide
    	  model = ModelFactory.createDefaultModel();

    	 // utiliser le FileManager pour trouver le fichier d'entrée
    	 InputStream in = FileManager.get().open( inputFileName );
    	if (in == null) {
    	    throw new IllegalArgumentException(
    	                                 "Fichier: " + inputFileName + " non trouvé");
    	}

    	// lire le fichier owl
    	model.read(in, null);

    	// l'écrire sur la sortie standard
    	//model.write(System.out);
       // System.out.println("load file : File/RDFS_file/OntologieMovie.owl");

    }

}
