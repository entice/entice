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
    "releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "Mandubian repository snapshots" at "https://github.com/mandubian/mandubian-mvn/raw/master/snapshots/",
    "Mandubian repository releases" at "https://github.com/mandubian/mandubian-mvn/raw/master/releases/"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.2.0",
    "play" %% "play-json" % "2.2-SNAPSHOT"
)

