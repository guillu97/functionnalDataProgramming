package com.funct.project.utils

import play.api.libs.json.{JsObject, Json, JsValue}

import scala.io.Source

/**
  * Utility script for parsing sample json input
  */
object JsonParser {
  val fileContents = Source.fromFile("src/test/resources/consumer_scooter.json").getLines.mkString
  val json = Json.parse(fileContents).asInstanceOf[JsObject]
  System.out.println("Json from the file:" + json.toString())
  def getRecordString: String = json.toString()
  def getRecordObject: JsObject = json
}