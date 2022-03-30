package com.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleMessage {
    String Name;
    String Title;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "Name='" + Name + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }

    public SampleMessage(){
    }

    public SampleMessage(String name, String title) {
        this.Name = name;
        this.Title = title;
    }

}
