package io.mem0r1es.trank.ranking

import java.net.URI

import scala.collection.Seq
import scala.collection.immutable.Map

class DEPTH extends RankingAlgo {

  /**
   * Rank types by inverse-sort on the hierarchy level.
   */
  override def rank(entityTypes: Map[URI, HierInfo]): Seq[URI] =
    entityTypes.toSeq.sortBy(_._2.level).reverse.map(_._1)
}
