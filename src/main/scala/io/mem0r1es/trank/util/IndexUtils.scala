package io.mem0r1es.trank.util

import java.io.File

import com.typesafe.config.Config
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.NIOFSDirectory


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
    val reader = DirectoryReader.open(directory)
    new IndexSearcher(reader)
  }
}
