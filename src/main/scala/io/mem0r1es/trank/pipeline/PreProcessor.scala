package io.mem0r1es.trank.pipeline

import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.extractors.DefaultExtractor

object PreProcessor {

  /**
   * Runs the content pre-processing step (e.g., HTML boilerplate removal)
   */
  def preProcess(content: String): String = {
    boilerpipe(content)
  }

  private def boilerpipe(content: String): String = {
    val articleExtr = Option(ArticleExtractor.INSTANCE.getText(content))
    val defaultExtr = Option(DefaultExtractor.INSTANCE.getText(content))

    // gives priority to the ArticleExtractor, allegedly leading to better results
    if (articleExtr.getOrElse("") nonEmpty)
      return articleExtr.get
    if (defaultExtr.getOrElse("") nonEmpty)
      return defaultExtr.get

    return content
  }
}
