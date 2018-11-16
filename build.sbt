name := "scala-json-benchmark"

version := "1.0.1"

scalaVersion := "2.12.7"

resolvers ++= Seq(
  "Maven repo" at "http://repo1.maven.org/maven2",
  "Spring repo" at "http://repo.springsource.org/libs-release",
  "Apache repo" at "https://repository.apache.org/content/repositories/releases",
  "JBoss repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss",
  "Sonatype repo" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Mvnrepository" at "http://mvnrepository.com/artifact"
)

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.6.2",
  "io.spray" %% "spray-json" % "1.3.5",
  "net.liftweb" %% "lift-json" % "3.3.0",
  "com.typesafe.play" %% "play-json" % "2.6.10",
  "io.circe" %% "circe-core" % "0.10.0",
  "io.circe" %% "circe-generic" % "0.10.0",
  "io.circe" %% "circe-parser" % "0.10.0",
  "io.argonaut" %% "argonaut" % "6.2.2",
  "io.argonaut" %% "argonaut-scalaz" % "6.2.2",
  "io.argonaut" %% "argonaut-monocle" % "6.2.2",
  "io.argonaut" %% "argonaut-cats" % "6.2.2"
)

enablePlugins(JmhPlugin)

