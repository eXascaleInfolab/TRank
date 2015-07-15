organization := "io.mem0r1es"

name := "TRank"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")


// --------------------
// --- Dependencies ---
// --------------------

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

// CoreNLP + resources
libraryDependencies ++= Seq(
    "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2",
    "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" classifier "models"
)

// Lucene deps
libraryDependencies ++= Seq(
    "org.apache.lucene" % "lucene-core" % "4.10.4",
    "org.apache.lucene" % "lucene-analyzers-common" % "4.10.4",
    "org.apache.lucene" % "lucene-queries" % "4.10.4"
)

// Misc
libraryDependencies ++= Seq(
    "org.apache.tika" % "tika-core" % "1.7",
    "org.apache.tika" % "tika-parsers" % "1.7",
    "commons-io" % "commons-io" % "2.4",
    "com.typesafe" % "config" % "1.2.1",
    "com.typesafe.play" %% "play-json" % "2.3.8",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

