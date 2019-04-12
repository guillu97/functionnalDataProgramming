package com.funct.project

import com.funct.project.consumer.Consumer
import com.funct.project.producer.Producer
//import com.funct.project.spark.SparkFunct.SparkFunct
import com.funct.project.utils.JsonParser
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scalatest.FunSuite

/**
  * Integration test for producing to and consuming from docker kafka instance
  */
class TestProduceAndConsumeIT extends FunSuite {
  val topic = "test-records"
  val records = JsonParser.getRecordString
  val producer = new Producer()
  val msgFunc = (cr: ConsumerRecord[String, String]) => println(cr.value())
  val consumer = new Consumer(List(topic), math.random.toString, msgFunc = msgFunc)
  //val spark = new SparkFunct()

  test("can produce and consume records from Kafka") {
    producer.produce(topic, records)
    consumer.consume()

    Thread.sleep(2000)  // need to tell scalatest to wait a few seconds
  }
}
