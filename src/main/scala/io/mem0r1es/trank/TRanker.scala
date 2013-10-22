package io.mem0r1es.trank

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import io.mem0r1es.trank.pipeline.EntityLinking.linkEntities
import io.mem0r1es.trank.pipeline.NER.runNER
import io.mem0r1es.trank.pipeline.PreProcessor.preProcess
import io.mem0r1es.trank.pipeline.TypeRanking.rankTypes
import io.mem0r1es.trank.pipeline.TypeRetrieval.retrieveTypes
import io.mem0r1es.trank.ranking.ANCESTORS
import io.mem0r1es.trank.ranking.RankingAlgo

class TRanker(content: String, rankingAlgo: RankingAlgo, config: Config) {

  config.checkValid(ConfigFactory.defaultReference(), "TRank")

  /**
   * Default to ANCESTORS ranking algorithm.
   */
  def this(content: String) {
    this(content, new ANCESTORS, ConfigFactory.load())
  }

  val contentRaw = content

  // TRank pipeline steps
  val contentPreProcessed = preProcess(content)
  private val entityLabels = runNER(contentPreProcessed)
  val entityToLabel = linkEntities(entityLabels, config)
  val entityURIs = entityToLabel.keySet
  val entityToTypes = retrieveTypes(entityURIs, config)
  val entityToTRankedTypes = rankTypes(entityToTypes, rankingAlgo, config)
}
