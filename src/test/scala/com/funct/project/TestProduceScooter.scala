package com.funct.project

import com.funct.project.producer.Producer
import com.funct.project.utils.JsonParser
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scalatest.FunSuite

/**
  * Integration test for producing to and consuming from docker kafka instance
  */
class TestProduceScooter extends FunSuite {
  val topic = "test-records"
  val recordStr = JsonParser.getRecordString
  val recordObj = JsonParser.getRecordObject
  val producer = new Producer()
  val msgFunc = (cr: ConsumerRecord[String, String]) => println(cr.value())

  System.out.println(recordObj.value("coord"))


  test("can produce and consume records from Kafka") {
    System.out.println("I am now producing")
    producer.produce(topic, recordStr)

    Thread.sleep(2000)  // need to tell scalatest to wait a few seconds
  }
}
