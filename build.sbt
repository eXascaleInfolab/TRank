organization := "io.mem0r1es"

name := "TRank"

version := "1.0"

scalaVersion := "2.10.2"


// --------------------
// --- Dependencies ---
// --------------------
libraryDependencies += "de.l3s.boilerpipe" % "boilerpipe" % "1.2.0"

resolvers += "Boilerpipe Maven Repository" at "http://boilerpipe.googlecode.com/svn/repo/"

// The maven package for boilerpipe does not specify the needed dependencies,
// so we have to import them manually...
// --- NEEDED BY BOILERPIPE ---
libraryDependencies += "net.sourceforge.nekohtml" % "nekohtml" % "1.9.13"

libraryDependencies += "xerces" % "xercesImpl" % "2.9.1"

// CoreNLP + resources
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "1.3.5"

libraryDependencies += "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-parse-models" % "1.3.5"

libraryDependencies += "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-postag-models" % "1.3.5"

libraryDependencies += "edu.washington.cs.knowitall.stanford-corenlp" % "stanford-ner-models" % "1.3.5"

// Lucene deps
libraryDependencies += "org.apache.lucene" % "lucene-core" % "4.5.0"

libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "4.5.0"

libraryDependencies += "org.apache.lucene" % "lucene-queries" % "4.5.0"

// Misc
libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "com.typesafe" % "config" % "1.0.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.2" % "test"

// Plugin settings
EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
