package io.mem0r1es.trank.pipeline

import io.mem0r1es.trank.pipeline.PreProcessor._
import org.scalatest.FlatSpec
import scala.io.Source
import java.io.ByteArrayInputStream
import java.io.FileInputStream

class PreProcessorSpec extends FlatSpec {
  
  val htmlStr = Source.fromFile("src/test/resources/exascale.info.html").mkString
  val txtStr = Source.fromFile("src/test/resources/exascale.info.txt").mkString

  "A PreProcessor" should "remove boilerplate from HTML content" in {
    val html = new FileInputStream("src/test/resources/exascale.info.html")
    assert(preProcess(html).trim === txtStr.trim)
  }

  it should "leave intact textual content" in {
    val txt = new FileInputStream("src/test/resources/exascale.info.txt")
    assert(preProcess(txt).trim === txtStr.trim)
  }

  it should "not fail with empty content" in {
    assert(preProcess(new ByteArrayInputStream("".getBytes())) === "")
  }
}
