package com.demo.route;

import com.demo.model.SampleMessage;
import com.demo.model.SampleResponse;
import com.demo.processor.SampleResponseProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.SampleModel;

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
