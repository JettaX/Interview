package org.sl;

import org.sl.Q1.Person;
import org.sl.Q3.Figure;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .firstName("John")
                .lastName("Doe")
                .middleName("Middle")
                .country("USA")
                .address("Some address")
                .phone("1234567890")
                .age(25)
                .gender("Male")
                .build();
        System.out.println(person);

        List<Figure> figures = List.of(
                new org.sl.Q3.Triangle(),
                new org.sl.Q3.Square(),
                new org.sl.Q3.Circle()
        );

        for (Figure figure : figures) {
            figure.draw();
            figure.rotate();
            figure.scale();
            figure.zoom();
        }
    }
}