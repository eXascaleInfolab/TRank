package io.mem0r1es.trank.pipeline

import java.net.URI

import scala.Array.canBuildFrom

import org.apache.lucene.index.Term
import org.apache.lucene.search.TermQuery

import com.typesafe.config.Config

import io.mem0r1es.trank.util.IndexUtils
import io.mem0r1es.trank.util.TRankIndexType

object TypeRetrieval {

  /**
   * Given a DBpedia resource URI, retrieve all its RDF types. 
   */
  def retrieveTypes(entities: Set[URI], config: Config): Map[URI, Set[URI]] = {
    var typedEntities = Map[URI, Set[URI]]()

    entities.foreach { entity =>
      val types = getTypes(entity, config)
      typedEntities += entity -> types
    }
    typedEntities
  }

  private def getTypes(entity: URI, config: Config): Set[URI] = {
    val searcher = IndexUtils.getIndexSearcher(TRankIndexType.TYPE_INDEX, config)

    val query = new TermQuery(new Term("uri", entity.toString))
    val docs = searcher.search(query, 1)
    if (docs.scoreDocs.length > 0) {
      val d = searcher.doc(docs.scoreDocs(0).doc)
      d.getValues("type").map(new URI(_)).toSet
    } else {
      Set[URI]()
    }
  }
}
