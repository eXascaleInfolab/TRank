package io.mem0r1es.trank.ranking

import java.net.URI

import org.scalatest.FlatSpec

class ANCESTORSSpec extends FlatSpec {

  val type1 = (new URI("http://type1"),
    new HierInfo(2, Seq[URI](new URI("http://path1"), new URI("http://path2"))))
  val type2 = (new URI("http://type2"),
    new HierInfo(5, Seq[URI](new URI("http://type1"), new URI("http://path3"))))
  val type3 = (new URI("http://type3"),
    new HierInfo(4, Seq[URI](new URI("http://type1"), new URI("http://type2"))))

  "An ANCESTORS ranker" should "rank types properly" in {
    val ranked = new ANCESTORS().rank(Map(type1, type2, type3))
    assert(ranked(0)._1.toString === "http://type3")
    assert(ranked(0)._2 === 2)
  }

  it should "not fail when no types are provided" in {
    new ANCESTORS().rank(Map[URI, HierInfo]())
  }
}
