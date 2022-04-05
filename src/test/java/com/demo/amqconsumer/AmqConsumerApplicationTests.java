package com.demo.amqconsumer;

import com.demo.amqconsumer.model.SampleMessage;
import com.demo.amqconsumer.processor.SampleResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.engine.DefaultProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@EnableAutoConfiguration
@SpringBootTest
class AmqConsumerApplicationTests {

	@Test
	void contextLoads() {
	}

	ProducerTemplate producerTemplate;

	CamelContext camelContext = new DefaultCamelContext();

	@Autowired
	SampleResponseProcessor sampleResponseProcessor;

	@EndpointInject("mock:test")
	MockEndpoint mockEndpoint;

	@Value("classpath:sampleResponse.json")
	Resource testResponse;

	@Value("classpath:sampleMessage.json")
	Resource testMessage;

	String sampleMessage;
	String sampleResponse;

	JacksonDataFormat stringFormat = new JacksonDataFormat(SampleMessage.class);


	@BeforeEach
	public void setUp() throws Exception {

		producerTemplate = camelContext.createProducerTemplate();

		sampleResponse = FileUtils.readFileToString(testResponse.getFile(), Charset.defaultCharset());
		sampleMessage = FileUtils.readFileToString(testMessage.getFile(), Charset.defaultCharset());

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:yeehaw")
						.unmarshal(stringFormat)
						.process(sampleResponseProcessor)
						.to(mockEndpoint)
						.end();
			}
		});

	}

	@Test
	public void sampleResponseProcessorTest() throws IOException, JSONException {

		camelContext.start();
		producerTemplate.sendBody("direct:yeehaw", sampleMessage);
		mockEndpoint.expectedBodiesReceived(1);
		camelContext.stop();
	}


}
