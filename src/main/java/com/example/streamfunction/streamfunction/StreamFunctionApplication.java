package com.example.streamfunction.streamfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamFunctionApplication {

	@Autowired
	private Sink sink;

	public static void main(String[] args) {
		SpringApplication.run(StreamFunctionApplication.class, "--spring.cloud.stream.function.definition=storeSync");
	}

	@Bean
	public Consumer<String> storeSync() {
		return this::store;
	}

	public boolean store(String paylaod){
	    System.out.println(paylaod);
        boolean send = this.sink.input().send(MessageBuilder.withPayload(paylaod).build());
        System.out.println(send);
		return send;
	}

}
