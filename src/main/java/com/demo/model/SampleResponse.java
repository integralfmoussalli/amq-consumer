package com.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleResponse {

    String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public SampleResponse(String response) {
        Response = response;
    }

    public SampleResponse() {
    }










}
