# Functional Data Programming Project
### By Julien DOS SANTOS, Guillaume FARGE and Claire HUANG

## Concept
Generation of datas from " Paris' Scooter Wheel" <br/>
We will use Scala, Kafka and Spark to generate all of this.<br/>

## Run Kafka
First of all in order to run kafka, you have to do the following steps:<br/>
<br/>
In your downlowed package, you have to :<br/>

* 1/ Run the Zookeeper 

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

* 2/ Run the Kafka server
```
bin/kafka-server-start.sh config/server.properties
```

* 3/ 


Cheat Sheet: 

* Create a topic :

```
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```
* list of all topics

```
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

* Create a producer

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```
* Create a consumer 

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```
## Producer 
