All RDFS file need to be here.

Actor : contains the rdfs for Actors.
Movies : contains the rdfs  for Movie.

RDFS : list of classes and properties for an object.

an example of request :

prefix mo: <http://www.semanticweb.org/titanium/ontologies/2017/0/untitled-ontology-11#>
select ?label ?comment where {
  ?uri rdfs:label ?label.
  ?uri rdfs:comment ?comment.
  ?uri mo:isTypeOf mo:Fantasy.
  filter not exists {?uri mo:wasProductby mo:Jerry_Bruckheimer}.
  filter not exists {?uri mo:wasReleasedIn 2002}.
}