name := "Entice Protocol"

version := "0.0.1"

scalaVersion := "2.10.2"

resolvers ++= Seq(
    "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    "releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "Mandubian repository snapshots" at "https://github.com/mandubian/mandubian-mvn/raw/master/snapshots/",
    "Mandubian repository releases" at "https://github.com/mandubian/mandubian-mvn/raw/master/releases/"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.2.0",
    "play" %% "play-json" % "2.2-SNAPSHOT"
)
