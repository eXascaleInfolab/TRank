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
import java.io.InputStream
import scala.io.Source
import java.io.ByteArrayInputStream

class TRanker(content: InputStream, rankingAlgo: RankingAlgo, config: Config) {

  config.checkValid(ConfigFactory.defaultReference(), "TRank")
  
  /**
   * Default to standard config.
   */
  def this(content: InputStream, rankingAlgo: RankingAlgo) {
    this(content, rankingAlgo, ConfigFactory.load())
  }

  /**
   * Default to ANCESTORS ranking algorithm, and standard config.
   */
  def this(content: InputStream) {
    this(content, new ANCESTORS, ConfigFactory.load())
  }

  /**
   * Default to standard config.
   */
  def this(contentStr: String, rankingAlgo: RankingAlgo) {
    this(new ByteArrayInputStream(contentStr.getBytes()),
         rankingAlgo,
         ConfigFactory.load())
  }

  /**
   * Default to ANCESTORS ranking algorithm, and standard config.
   */
  def this(contentStr: String) {
    this(new ByteArrayInputStream(contentStr.getBytes()),
         new ANCESTORS,
         ConfigFactory.load())
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
