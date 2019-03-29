package com.example.streamfunction.streamfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

@SpringBootApplication
@EnableBinding(Source.class)
public class StreamFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamFunctionApplication.class, "--spring.cloud.stream.function.definition=storeSync");
	}


    @Bean
    public Consumer<String> storeSync(ApplicationContext context) {
        return v -> {
            System.out.println("payload is " + v);
            MessageChannel channel = context.getBean(Source.OUTPUT, MessageChannel.class);
            boolean send = channel.send(MessageBuilder.withPayload(v).build());
            System.out.println("send data " + send);
        };
    }

}
