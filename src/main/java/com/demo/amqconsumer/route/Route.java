package com.demo.amqconsumer.route;

import com.demo.amqconsumer.model.SampleMessage;
import com.demo.amqconsumer.model.SampleResponse;
import com.demo.amqconsumer.processor.SampleResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {

    @Autowired
    SampleResponseProcessor sampleResponseProcessor;

    JacksonDataFormat stringFormat = new JacksonDataFormat(SampleMessage.class);
    JacksonDataFormat jsonFormat = new JacksonDataFormat(SampleResponse.class);

    @Override
    public void configure() {
        from("activemq:queue:sendMessageQueue")
                .unmarshal(stringFormat)
                .process(sampleResponseProcessor)
                .marshal(jsonFormat)
                .log("{body}");
    }
}
