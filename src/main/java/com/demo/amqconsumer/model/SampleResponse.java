package com.demo.amqconsumer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleResponse {

    String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public SampleResponse(String response) {
        this.response = response;
    }

    public SampleResponse() {
    }










}
