package io.mem0r1es.trank.pipeline

import io.mem0r1es.trank.pipeline.NER._
import org.scalatest.FlatSpec
import scala.io.Source

class NERSpec extends FlatSpec {

  "A NER" should "extract entity labels" in {
    val content = Source.fromFile("src/test/resources/exascale.info.txt").mkString
    val entities = runNER(content)
    assert(entities contains ("Switzerland"))
    assert(entities contains ("University of Fribourg"))
  }

  it should "not fail with content without Named Entities" in {
    val content = "Just some basic text without any named entities."
    val entities = runNER(content)
    assert(entities.isEmpty)
  }

  it should "not fail with empty content" in {
    val entities = runNER("")
    assert(entities.isEmpty)
  }
}
