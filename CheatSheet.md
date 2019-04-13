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
