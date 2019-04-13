package com.funct.project

import com.funct.project.producer.Producer
import com.funct.project.utils.JsonParser
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.scalatest.FunSuite
import play.api.libs.json
import play.api.libs.json.{JsObject, JsValue, Json, _}
import com.funct.project.scooter.{Coord, Date, Km, Scooter}

/**
  * Integration test for producing to and consuming from docker kafka instance
  */
class TestProduceScooter extends FunSuite {
  val topic = "test-records"
  val recordStr = JsonParser.getRecordString
  val recordObj = JsonParser.getRecordObject
  val producer = new Producer()
  val msgFunc = (cr: ConsumerRecord[String, String]) => println(cr.value())


  /*
    var id: Int = 0
    var coord: Coord = new Coord()
    var battery: Int = 100
    var km: Km = new Km()
    var timeInUse: Int = 0
    var lastDayUsed : Date = new Date()
    var inUse: Boolean = false
    var inCharge: Boolean = false
    var speed: Int = 0
   */

  val rand = scala.util.Random

  val scooter = new Scooter()
  scooter.id = rand.nextInt(1000000000)
  scooter.coord = new Coord(rand.nextDouble()*(48.89972  - 48.81708) + 48.81708 ,rand.nextDouble() * (2.292335 - 2.369485) + 2.369485)
  scooter.battery = rand.nextInt(101)
  scooter.km = new Km(rand.nextInt(3000), rand.nextInt(100))
  scooter.timeInUse = rand.nextInt(5 * 60)
  scooter.inUse = rand.nextBoolean()
  scooter.inCharge = !scooter.inUse

  if(scooter.inUse)
    scooter.speed = rand.nextInt(50) // 0-49

  System.out.println("test:" + scooter.toJsonString())




  System.out.println("???????????????????" + (recordObj \ "coord" \ "lat").get)


  /*
  //val t = 50
  // figure out path and the field which you want to update
  val temp1 = (__ \ 'id ).json.put(JsString("50"))
  recordObj.transform(temp1)
  */


  System.out.println("???????????????????" + (recordObj \ "id").get)

  test("can produce and consume records from Kafka") {
    while(true) {
      System.out.println("I am now producing")
      producer.produce(topic, scooter.toJsonString())
      scooter.ScooterMove()

      Thread.sleep(2000) // need to tell scalatest to wait a few seconds
    }
  }
}
