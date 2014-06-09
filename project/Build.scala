/**
 * For copyright information see the LICENSE document.
 */

import sbt._
import sbt.Keys._


object ProjectBuild extends Build {

  val project  = "0.1.0"
  val scala    = "2.11.0"
  val akka     = "2.3.3"


  val prjSettings = Project.defaultSettings ++ Seq(
    version      := project,
    scalaVersion := scala,

    scalacOptions ++= Seq(
      "-unchecked",
      "-feature",
      "-deprecation",
      "-Xlint",
      "-encoding", "UTF-8"
    ),
    
    resolvers ++= Seq(
      "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      "releases"  at "http://oss.sonatype.org/content/repositories/releases",
      "typesafe"  at "http://repo.typesafe.com/typesafe/releases/"
    ),

    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"     % akka,
      "org.scala-lang"    %% "scala-pickling" % "0.9.0-SNAPSHOT"
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