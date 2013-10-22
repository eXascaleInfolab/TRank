package io.mem0r1es.trank.util

import org.apache.lucene.search.IndexSearcher
import com.typesafe.config.Config
import org.apache.lucene.store.NIOFSDirectory
import org.apache.lucene.index.IndexReader
import java.io.File


object IndexUtils {

  import TRankIndexType._
  private var searcherCache = Map[TRankIndexType, IndexSearcher]()

  def getIndexSearcher(indexType: TRankIndexType, config: Config): IndexSearcher = {
    val searcher = searcherCache.get(indexType)
    
    searcher match {
      case Some(value) => value
      case None => {
        val value = createIndexSearcher(indexType, config)
        searcherCache += indexType -> value
        value
      }
    }
  }
  
  private def createIndexSearcher(indexType: TRankIndexType, config: Config): IndexSearcher = {
    val indexPath = new File(config.getString("TRank.index_basepath") + "/" + indexType)
    val directory = new NIOFSDirectory(indexPath)
    val reader = IndexReader.open(directory)
    new IndexSearcher(reader)
  }
}
