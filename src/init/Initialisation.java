package init;

import fr.inria.edelweiss.kgraph.core.Graph;
import fr.inria.edelweiss.kgtool.load.Load;
import fr.inria.edelweiss.kgtool.load.LoadException;

import java.io.InputStream;

/**
 * class for load file and start process
 *
 * Created by titanium on 10/01/2017.
 */
public class Initialisation {

    public static Graph graph = Graph.create(true);


    public Initialisation(){
        loadfile();
    }
    /**
     * load file for Graph
     */
    public void loadfile (){
        Load load = Load.create(graph);
        try {

            InputStream stream = Load.class.getResourceAsStream("/OntologieMovie.owl");
            if(stream == null) {
                System.out.println("input stream == nul");
            }
            //not sure about the path
            load.loadResource("File/RDFS_file/OntologieMovie.owl",0);
        } catch (LoadException e) {
            e.printStackTrace();

        }
       // System.out.println("load file : File/RDFS_file/OntologieMovie.owl");

    }

}
