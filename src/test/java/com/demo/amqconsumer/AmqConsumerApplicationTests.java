package com.demo.amqconsumer;

import com.demo.amqconsumer.processor.SampleResponseProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.camel.builder.Builder.constant;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
class AmqConsumerApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	ConsumerTemplate consumerTemplate;

	@Autowired
	private CamelContext camelContext;

	@Autowired
	SampleResponseProcessor sampleResponseProcessor;

	@EndpointInject("mock:test")
	MockEndpoint mockEndpoint;

	@Value("classpath:sampleResponse.json")
	Resource testResponse;

	@Value("classpath:sampleMessage.json")
	Resource testMessage;


	@BeforeTestClass
	public void setConsumerTemplate() throws IOException {

		String message = FileUtils.readFileToString(testMessage.getFile(), Charset.defaultCharset());

		RouteBuilder routeBuilder = new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("activemq:queue:sendMessageQueue")
						.setBody(constant(message))
						.process(sampleResponseProcessor)
						.to(mockEndpoint)
						.end();
			}
		};
	}

	@Test
	public void sampleResponseProcessorTest() throws IOException, JSONException {
		consumerTemplate.receiveBody("activemq:queue:sendMessageQueue",10);
		mockEndpoint.expectedBodiesReceived(1);
	}


}
