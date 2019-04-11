package com.funct.project


import com.funct.project.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scalatest.FunSuite

/**
  * Integration test for producing to and consuming from docker kafka instance
  */
class TestConsumeScooter extends FunSuite {
  val topic = "test-records"
  val msgFunc = (cr: ConsumerRecord[String, String]) => println(cr.value())
  val consumer = new Consumer(List(topic), math.random.toString, msgFunc = msgFunc)



  test("can produce and consume records from Kafka") {
    System.out.println("I am now consuming")
    consumer.consume()

    Thread.sleep(2000)  // need to tell scalatest to wait a few seconds
  }
}
