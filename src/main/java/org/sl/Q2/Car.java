package org.sl.Q2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Car implements CarMethods {
    private String color;
    private String name;

    @Override
    public void start() {
        System.out.println("Car starting");
    }
}
