package io.mem0r1es.trank.ranking

import java.net.URI

trait RankingAlgo {

  def rank(entityTypes: Map[URI, HierInfo]): Seq[(URI, Double)]
}