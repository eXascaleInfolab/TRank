package io.mem0r1es.trank.ranking

import java.net.URI

class ANCESTORS extends RankingAlgo {

  /**
   * Rank types by inverse-sort on the # of ANCESTORS contained in the type set.
   */
  override def rank(entityTypes: Map[URI, HierInfo]): Seq[URI] = {
    val allTypes = entityTypes.keySet

    def score(path: Seq[URI]): Int = {
      path filter (allTypes.contains(_)) length
    }

    entityTypes.map {
      case (k, v) => (k, score(v.path))
    }.toSeq.sortBy(_._2).reverse.map(_._1)
  }
}
