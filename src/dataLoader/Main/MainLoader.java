package dataLoader.Main;


import org.apache.jena.query.*;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.log4j.varia.NullAppender;

import static org.apache.jena.query.QueryFactory.*;
/**
 * Created by titanium on 18/01/2017.
 */
public class MainLoader {

    public static void main(String[] args) {
        //Récupère les labels des films
        String qMovie = "SELECT DISTINCT ?uri ?label WHERE { ?uri <http://www.w3.org/2000/01/rdf-schema#label> ?label. ?uri a <http://data.linkedmdb.org/resource/movie/film>.  } LIMIT 10";

        // Récupère les scriptsWriter pour un film
        String qSW = "SELECT DISTINCT ?label WHERE { ?uri <http://www.w3.org/2000/01/rdf-schema#label> ?label. ?uri a <http://data.linkedmdb.org/resource/movie/film>.  } LIMIT 10";
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        System.out.println("Hello World!");
        Model m = ModelFactory.createDefaultModel();
        Query query = create(qMovie);
        QueryExecution queryexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
        ResultSet resultSet = queryexec.execSelect();
        while (resultSet.hasNext()) {
            QuerySolution binding = resultSet.nextSolution();
            Literal subj = binding.getLiteral("label");
            Resource subj2 = (Resource) binding.get("uri");
            System.out.println("movie : "+ subj.getString() + ", uri : "+ subj2.getURI() );

        }
    }
}
