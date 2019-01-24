package org.jianyun.producer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ProducerTest {
	  private final KafkaTemplate<Integer, String> kafkaTemplate;
	  @Autowired
	  ProducerTest(KafkaTemplate kafkaTemplate){
		  this.kafkaTemplate = kafkaTemplate;
	  }
	  @KafkaListener(topics = {"topicKafka"})
	    public void processMessage(ConsumerRecord<Integer, String> record) {
		  log.info("kafka processMessage start");
		  log.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());


		  log.info("kafka processMessage end");
	  	}
	  public void sendMessage(String topic, String data) {
		    log.info("kafka sendMessage start");
	        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
	        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
	            @Override
	            public void onFailure(Throwable ex) {
	            	log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
	            }

	            @Override
	            public void onSuccess(SendResult<Integer, String> result) {
	            	log.info("kafka sendMessage success topic = {}, data = {}",topic, data);
	            }
	        });
	        log.info("kafka sendMessage end");
	    }

}
