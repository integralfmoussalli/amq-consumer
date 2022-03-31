package com.demo.amqconsumer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SampleMessage {
    String name;
    String colour;

    @Override
    public String toString() {
        return "MessageResponse{" +
                "Name='" + name + '\'' +
                ", Title='" + colour + '\'' +
                '}';
    }

    public SampleMessage(){
    }

    public SampleMessage(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

}
