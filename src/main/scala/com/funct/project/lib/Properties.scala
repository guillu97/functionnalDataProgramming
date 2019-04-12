package com.funct.project.lib

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer


object Properties {
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("group.id", "consumer-tutorial")
  props.put("key.deserializer", classOf[StringDeserializer])
  props.put("value.deserializer", classOf[StringDeserializer])

  val kafkaConsumer = new KafkaConsumer[String, String](props)

  kafkaConsumer.subscribe(util.Arrays.asList("foo", "bar"))
}
