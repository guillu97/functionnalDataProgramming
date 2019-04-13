# Functional Data Programming Project
### By Julien DOS SANTOS, Guillaume FARGE and Claire HUANG

## Concept
Our team decided to imagine the scocter wheel as devices.Every 2 seconds, a producer( scooter wheel ) will send somes datas concerning its devices. Otherwise, the consumer will catch this data thought Kafka.
The SparkAnalyser is also a consumer but it is lauched every 10 secs.
In its analysis, he will take care of :
- get the average of time used on the scooterWheel
- get the number of scooter in the area predefined 
- get the average km done since "last charge"
- get the maximun speed of all scooter
- get the average spped of all scooter in Use
- get the number of scooter with less than 20% of battery

We will use Scala, Kafka and Spark to generate all of this.<br/>

## How to launch and test our project
First of all in order to run kafka, you have to do the following steps:<br/>
<br/>
In your downlowed package (kafka_2.12-2.2.0) you have to :<br/>

* 1/ Run the Zookeeper 

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

* 2/ Run the Kafka server
```
bin/kafka-server-start.sh config/server.properties
```

* 3/ Create your producers <br/>

*run multiple times "TestProducerScooter"*<br/>
If you want to call all at the same time, you can go in the "RUN/DEBUG Configurations" near the run button, and 
click on "+", choose "compound" and select multiple times "TestProducerScooter"<br/>




