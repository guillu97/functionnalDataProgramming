package com.funct.project.spark

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark._
import org.apache.spark.streaming._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkFunct extends App {
  /*case class SparkFunct(){
    println("testTest")
  }*/
  Logger.getLogger("org").

    setLevel(Level.ERROR)

  Logger.getLogger("akka").

    setLevel(Level.ERROR)


  var totalAvgTimeUse = 0

  def jsonStrToMap(jsonStr: String): Map[String, Any] = {
    implicit val formats = org.json4s.DefaultFormats

    parse(jsonStr).extract[Map[String, Any]]
  }

  def avgTimeUse(line : String) = {
    val linemap = jsonStrToMap(line)
    totalAvgTimeUse = totalAvgTimeUse + linemap("disc").toString.toInt
  }

  val conf = new SparkConf().setAppName("DirectStream").setMaster("local[*]")

  val ssc = new StreamingContext(conf, Seconds(20))

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("test-records")
  val stream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )

  val spark = SparkSession.builder().appName("Spark SQL").getOrCreate()

  stream.foreachRDD(rddRaw => {
    val rdd = rddRaw.map(_.value.toString)
    val df = spark.read.json(rdd)
    if(!df.isEmpty) {
      df.show()

      val avgTimeUsed = df.select(avg("timeInUse"))
      println("\n"+"Nb = " + avgTimeUsed.show() + "\n")

      val lowBattery =
          df
          .select("id")
            .filter("battery < 20")
          .distinct()
          .count()

      println("\n" + "Number of device with low battery : " + lowBattery + "\n")

      val avgKmLastCharge =
        df.select(avg("km.afterLastCharge"))
      println("\n"+"km last charge = " + avgKmLastCharge.show() + "\n")

      df.collect.foreach(println)

      val avgSpeedInUse =
        df.filter("inUse == 'true'").select(avg("speed"))
      println("\n"+"avg speed scooter in use = " + avgSpeedInUse.show() + "\n")

      val maxSpeedAll =
        df.groupBy("id")
          .agg(max("speed"))
      println("\n max speed = " + maxSpeedAll.show())


      df.select("currentDayTime").show()

      val positionLat = 48.8584
      val positionLong = 2.33091
      val size = 0.03
      val str = "(coord.lat > "+ (positionLat - size) +" AND coord.lat < "+ (positionLat + size) + ") AND (coord.long > "+ (positionLong - size) + " AND coord.long < " + (positionLong + size) + ")"
      val inArea = df.filter(str)
          .select("id")
          .distinct()
          .count()
      println("\n" + "Number of device with in area : " + inArea + "\n")

    }
  })
  ssc.start()
  ssc.awaitTermination()
}
