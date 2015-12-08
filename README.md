TRank [![Build Status](https://travis-ci.org/XI-lab/TRank.png?branch=master)](https://travis-ci.org/XI-lab/TRank)
=====
TRank implements a Scala pipeline for:
* boilerplate removal on markup content
* Named Entity Recognition
* Entity linkage with *DBpedia* URIs
* Entity typing using a novel type hierarchy that combines *DBpedia*, *Yago*, and *schema.org* classes
* Type ranking based on algorithms that underwent thorough evaluation via crowdsourcing
 

For example, a document containing the label *University of Fribourg* will return:
```scala
http://dbpedia.org/resource/University_of_Fribourg ->

Seq(http://dbpedia.org/class/yago/UniversitiesInSwitzerland,
http://dbpedia.org/class/yago/PuBlicUniversities,
http://schema.org/CollegeOrUniversity,
http://dbpedia.org/ontology/University,
http://dbpedia.org/ontology/EducationalInstitution,
http://schema.org/EducationalOrganization,
http://dbpedia.org/ontology/Organisation,
http://schema.org/Organization,
http://dbpedia.org/ontology/Agent)
```

How To Use TRank
----------------
### API
To use TRank, it is enough to create a TRanker object with any textual content:
```scala
class TRanker(content: String)
```
possibly specifying an alternative ranking algorithm, instead of the default ANCESTORS:
```scala
class TRanker(content: String, rankingAlgo: RankingAlgo)

trait RankingAlgo { def rank(???): Seq[URI] }
```

The results of the whole pipeline process are accessible through:
```scala
TRanker.entityToTRankedTypes: Map[URI, Seq[URI]]
```
for the final step, and through similar data structures for all the intermediate steps.

### Indexes
TRank requires 3 Lucene indexes that are available for
[download here](http://ftp.exascale.info/sites/default/files/uploaded/trank/trank-indexes.tgz).
The .tgz can be extracted in the classpath of the library, and TRank will start to use seamlessly the 3 indexes.

**IMPORTANT:** do not change the directory structure of `trank-indexes/`.


Alternatively, TRank uses the [Typesafe Configuration](https://github.com/typesafehub/config) library to manage user
settings. To override the default path to the indexes, it is enough to define the `TRank.index_basepath` property.


Background
----------
Much of Web search and browsing activity is today centered around entities. For this reason, Search Engine Result
Pages (SERPs) increasingly contain information about the searched entities such as pictures, short summaries,
related entities, and factual information. A key facet that is often displayed on the SERPs and that is instrumental
for many applications is the entity type. However, an entity is usually not associated to a single generic type
in the background knowledge bases but rather to a set of more specific types, which may be relevant or not given the
document context. For example, one can find on the Linked Open Data cloud the fact that Tom Hanks is a person, an actor,
and a person from Concord, California. All these types are correct but some may be too general to be interesting (e.g.,
person), while other may be interesting but already known to the user (e.g., actor), or may be irrelevant given the
current browsing context (e.g., person from Concord, California). In this paper, we define the new task of ranking entity
types given an entity and its context. We propose and evaluate new methods to find the most relevant entity type based on
collection statistics and on the graph structure interconnecting entities and types. An extensive experimental evaluation
over several document collections at different levels of granularity (e.g., sentences, paragraphs, etc.) and different
type hierarchies (including DBPedia, Freebase, and schema.org) shows that hierarchy-based approaches provide more accurate
results when picking entity types to be displayed to the end-user.


For more information, check the [ISWC2013 paper](http://ftp.exascale.info/sites/default/files/entityTypes.pdf).

