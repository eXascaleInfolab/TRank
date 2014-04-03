package io.mem0r1es.trank.ranking

import java.net.URI

import org.scalatest.FlatSpec

class DEPTHSpec extends FlatSpec {
  
  val type1 = (new URI("http://type1"), 
               new HierInfo(2, Seq[URI](new URI("http://path1"), new URI("http://path2")))
              )
  val type2 = (new URI("http://type2"), 
               new HierInfo(4, Seq[URI](new URI("http://path2"), new URI("http://path3")))
              )


  "A DEPTH ranker" should "rank types by maximum depth" in {
    val ranked = new DEPTH().rank(Map(type1, type2))
    assert(ranked(0)._1.toString === "http://type2")
    assert(ranked(0)._2 === 4)
  }
  
  it should "not fail when no types are provided" in {
    new DEPTH().rank(Map[URI, HierInfo]())
  }
}
