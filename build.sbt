name := "functionnalDataProgramming"
/*
version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.0.1"
libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.5.6"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.21"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.1"
*/

version := "1.0"

scalaVersion := "2.11.8"//"2.11.8"
val sparkVersion = "2.4.0"//"2.1.0"
val jacksonVersion = "2.6.7.1"//"2.9.7"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"
libraryDependencies += "org.apache.kafka" % "kafka_2.12" % "2.1.0" //"2.11"

libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.5.6"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.21"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"