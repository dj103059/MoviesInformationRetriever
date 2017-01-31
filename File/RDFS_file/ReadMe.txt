All RDFS file need to be here.

Actor : contains the rdfs for Actors.
Movies : contains the rdfs  for Movie.

RDFS : list of classes and properties for an object.

an example of request :

prefix mo:  <http://data.linkedmdb.org/resource/movie/>

prefix mo:  <http://data.linkedmdb.org/resource/movie/>
prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>
select ?label ?comment where {
  ?uri rdfs:label ?label.
  ?uri rdfs:comment ?comment.
  ?uri mo:isTypeOf mo:Fantasy.
  filter not exists {?uri mo:wasProductby mo:Jerry_Bruckheimer}.
  filter not exists {?uri mo:wasReleasedIn 2002}.
}

J'ai ma liste avec les movies , j'ai la requête.
je factorise la liste des movies
je créé une liste de Genre avec uri qui va avec .
je factorise la liste de genre .

je fais la fuction des listes par uri

même chose pour les acteurs et les perfomances

list to do :
- gerer le cas ou on a 0 retour de requête.
- récuperer le format lors de la recherche de propriété et de value
- mettre en place le systeme de poids avec "je ne sais pas" :
        - mettre des poids sur toute les branches et toutes les valeurs.(100 pour les propriétés, random pour les values)
        - faire une méthode qui set le poid d'une propriété s'il n' y a plus de value pour cette propriété
        - trouver un bon setting pour la décrémentation des poids sur les propriétés

=> ajouter les données du loader et enjoy.