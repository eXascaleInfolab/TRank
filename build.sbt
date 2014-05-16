organization := "io.mem0r1es"

name := "TRank"

version := "1.0"

scalaVersion := "2.10.2"


// --------------------
// --- Dependencies ---
// --------------------

// CoreNLP + resources
libraryDependencies ++= Seq(
    "edu.stanford.nlp" % "stanford-corenlp" % "1.3.5",
    "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-parse-models" % "1.3.5",
    "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-postag-models" % "1.3.5",
    "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-ner-models" % "1.3.5"
)

// Lucene deps
libraryDependencies ++= Seq(
    "org.apache.lucene" % "lucene-core" % "4.5.0",
    "org.apache.lucene" % "lucene-analyzers-common" % "4.5.0",
    "org.apache.lucene" % "lucene-queries" % "4.5.0"
)

// Misc
libraryDependencies ++= Seq(
    "org.apache.tika" % "tika-core" % "1.4",
    "org.apache.tika" % "tika-parsers" % "1.4",
    "commons-io" % "commons-io" % "2.4",
    "com.typesafe" % "config" % "1.0.2",
    "org.scalatest" %% "scalatest" % "1.9.2" % "test"
)

// Plugin settings
EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
