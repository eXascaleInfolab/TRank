package io.mem0r1es.trank.pipeline

import io.mem0r1es.trank.pipeline.PreProcessor._
import org.scalatest.FlatSpec
import scala.io.Source

class PreProcessorSpec extends FlatSpec {

  "A PreProcessor" should "remove boilerplate from HTML content" in {
    val html = Source.fromFile("src/test/resources/exascale.info.html").mkString.trim
    val txt = Source.fromFile("src/test/resources/exascale.info.txt").mkString.trim
    assert(preProcess(html).trim === txt)
  }

  it should "leave intact textual content" in {
    val txt = Source.fromFile("src/test/resources/exascale.info.txt").mkString.trim.replace("\n", "")
    assert(preProcess(txt).trim === txt)
  }

  it should "not fail with empty content" in {
    assert(preProcess("") === "")
  }
}
