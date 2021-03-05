name := "scalatestfx-build"

lazy val scalatestfx =
  Project(id = "scalatestfx", base = file("scalatestfx"))
    .settings(commonSettings: _*)
    .settings(
      description := "The ScalaTestFX Framework",
      publishArtifact := true,
      libraryDependencies ++= Seq(
        scalatest,
        testfxCore,
        scalafx,
        scalaCompat
      ) ++ javaFx
    )

lazy val scalatestfxDemos =
  Project(id = "scalatestfx-demos", base = file("scalatestfx-demos"))
    .settings(commonSettings: _*)
    .settings(
      description := "The ScalaTestFX Demonstrations",
      publishArtifact := false,
      javaOptions ++= Seq(
        "-Xmx512M",
        "-Djavafx.verbose"
      ),
      libraryDependencies ++= Seq(scalafx) ++ javaFx
    )
  .dependsOn (
    scalatestfx % "compile;test->test"
  )

lazy val scalatest = "org.scalatest" %% "scalatest" % "3.2.5" % Provided
lazy val testfxCore = "org.testfx" % "testfx-core" % "4.0.16-alpha"  % Provided
lazy val scalafx = "org.scalafx" %% "scalafx" % "15.0.1-R21" % Provided
lazy val scalaCompat = "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.1"

// Add OS specific JavaFX dependencies
val javafxModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}
lazy val javaFx = javafxModules.map(m => "org.openjfx" % s"javafx-$m" % "15.0.1"  % Provided classifier osName)


publishArtifact := false

//
// Common Settings
//
lazy val commonSettings = buildSettings

//
// Build Settings
//
lazy val buildSettings = Seq(
  scalaVersion := "2.13.5",
  scalacOptions ++= List("-release", "11"),
  parallelExecution := false,
  fork := true,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.bintrayRepo("haraldmaida", "maven")
  ),
  manifestSetting
)

lazy val manifestSetting = packageOptions += {
  Package.ManifestAttributes(
    "Created-By" -> "Simple Build Tool",
    "Built-By" -> Option(System.getenv("BUILT_BY")).getOrElse(System.getProperty("user.name")),
    "Build-Jdk" -> System.getProperty("java.version"),
    "Specification-Title" -> name.value,
    "Specification-Version" -> version.value,
    "Specification-Vendor" -> organization.value,
    "Implementation-Title" -> name.value,
    "Implementation-Version" -> version.value,
    "Implementation-Vendor-Id" -> organization.value,
    "Implementation-Vendor" -> organization.value
  )
}
