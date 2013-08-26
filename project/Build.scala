/**
 * For copyright information see the LICENSE document.
 */

import sbt._
import sbt.Keys._


object ProjectBuild extends Build {

    lazy val root = Project(
        id = "protocol",
        base = file("."),
        settings = Defaults.defaultSettings
    ) dependsOn(jsonMacros)

    lazy val jsonMacros = RootProject(uri("https://github.com/ephe-meral/akmacros-json.git#fix-play-2.2-SNAPSHOT"))
}