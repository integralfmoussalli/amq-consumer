package com.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleMessage {
    String Name;
    String Colour;

    @Override
    public String toString() {
        return "MessageResponse{" +
                "Name='" + Name + '\'' +
                ", Title='" + Colour + '\'' +
                '}';
    }

    public SampleMessage(){
    }

    public SampleMessage(String name, String title) {
        this.Name = name;
        this.Colour = title;
    }

}
