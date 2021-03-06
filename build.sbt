import sbt.Keys._

val playVersion = "2.4.2"

lazy val commonSettings = Seq(
  organization := "org.thisarattr.notifyme",
  scalaVersion := "2.11.6",
  EclipseKeys.preTasks := Seq(compile in Compile),
  routesGenerator := InjectedRoutesGenerator,
  resolvers ++= Seq(
    Resolver.mavenLocal,
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Maven Repository" at "http://repo1.maven.org/",
    "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
  ),
  publishMavenStyle := true,
  isSnapshot := true,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps"),
  parallelExecution in Test := false
)

lazy val root = (project in file(".")).enablePlugins(PlayScala).configs(IntegrationTest)
  .settings(commonSettings: _*)
  .aggregate(service, dao)
  .settings(Defaults.itSettings: _*)
  .settings(
    name := "notify-me",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      ws,
      "com.google.inject" % "guice" % "4.0",
      "org.mockito" % "mockito-core" % "1.10.19"
    ),
    publishArtifact := false,
    sourceDirectory in IntegrationTest := baseDirectory.value / "test-integration",
    scalaSource in IntegrationTest := baseDirectory.value / "test-integration"
  )

 
lazy val service = (project in file("./modules/service")) dependsOn dao settings(commonSettings: _*) settings(
  name := "notify-me-service",
  libraryDependencies ++= Seq(
  )
)
  
lazy val dao = (project in file("./modules/dao")) settings(commonSettings: _*) settings(
  name := "notify-me-dao",
  libraryDependencies ++= Seq(
    "org.reactivemongo" %% "reactivemongo" % "0.11.4",
    "org.scalatest" %% "scalatest" % "2.2.4",
    "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.47.0"
  )
)

