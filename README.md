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

* 3/ Create our topic :

```
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-records
```

* 4/ Create your producers <br/>
<br/>
*run multiple times "TestProducerScooter"*<br/>
If you want to call all at the same time, you can go in the "RUN/DEBUG Configurations" near the run button, <br/>
and click on "+", <br/>
choose "compound" and <br/>
select multiple times "TestProducerScooter"<br/>
Every 2 second, the producer will send data though kafka<br/>
<br/>
* 5/ Create your consummers and spark <br/>
<br/>
To run the spark consumer, you just have to launch the SparkFunct app in src/main/scala/spark/SparkFunct
Every 20 secondes, the analysis will be printed on the screen.
<br/>

## Our architecture
We didn't build any house today, but we apply what we have learn in classe into this project ! <br/>
When the producer is created, it will send the message to the consumer thought KAFKA. <br/>
The consumer will be able to receive the message because it use the KAFKA Topic that the producer used. <br/>
Here the consumer is obviously the company that lend their scooter wheel to clients. (Sometimes they need to get some informations about their devices).
<br/>
We decided to send and store the data as Json file because it's simplier to use
<br/>
About the analysis, we decided to use the dataframe functionalities because it integreated a lot of functionalities.
<br/>



