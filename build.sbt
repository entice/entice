name := "protocol"

version := "0.1.0"

scalaVersion := "2.10.2"

scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-deprecation",
    "-Xlint",
    "-target:jvm-1.6",
    "-encoding", "UTF-8"
)

resolvers ++= Seq(
    "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.2.0",
    "org.scala-lang" %% "scala-pickling" % "0.8.0-SNAPSHOT"
)
