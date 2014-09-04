/**
 * For copyright information see the LICENSE document.
 */

import sbt._
import sbt.Keys._


object ProjectBuild extends Build {

  val project  = "0.1.0"
  val scala    = "2.11.2"
  val akka     = "2.3.5"


  val prjSettings = Project.defaultSettings ++ Seq(
    version      := project,
    scalaVersion := scala,

    scalacOptions ++= Seq(
      "-unchecked",
      "-feature",
      "-deprecation",
      "-Xlint",
      //"-Xlog-implicits",
      "-encoding", "UTF-8"
    ),

    resolvers ++= Seq(
      "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "releases"  at "https://oss.sonatype.org/content/repositories/releases",
      "typesafe"  at "https://repo.typesafe.com/typesafe/releases/"
    ),

    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"     % akka,
      "org.scala-lang"    %% "scala-pickling" % "0.9.0-SNAPSHOT",
      "org.scalatest"     %% "scalatest"      % "2.1.3" % "test"
    )
  )


  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = prjSettings ++ Seq(
      name := "Entice Protocol"
    )
  )
}
