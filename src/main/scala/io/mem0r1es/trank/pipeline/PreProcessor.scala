package io.mem0r1es.trank.pipeline

import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.html.HtmlParser
import java.io.InputStream
import org.apache.tika.parser.ParseContext


object PreProcessor {

  /**
   * Runs the content pre-processing step (e.g., HTML tags removal)
   */
  def preProcess(content: InputStream): String = {
    extractTextFromHTML(content)
  }

  private def extractTextFromHTML(content: InputStream): String = {
    val handler = new BodyContentHandler()
    val metadata = new Metadata()
    new HtmlParser().parse(content, handler, metadata, new ParseContext())
    
    handler.toString
  }
}
