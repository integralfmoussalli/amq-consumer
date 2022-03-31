package com.demo.processor;

import com.demo.model.SampleMessage;
import com.demo.model.SampleResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SampleResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        SampleMessage sampleMessage = exchange.getIn().getBody(SampleMessage.class);
        if (!sampleMessage.getName().isEmpty() & !sampleMessage.getColour().isEmpty()){
            exchange.getIn().setBody(new SampleResponse("G'day " + sampleMessage.getName() + "! Your favourite colour is " + sampleMessage.getColour() + " :D"));
        } else {
            exchange.getIn().setBody(new SampleResponse("Uh oh, something has gone wrong x.x"));
        }

    }
}
