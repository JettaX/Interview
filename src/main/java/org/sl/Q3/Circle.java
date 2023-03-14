package org.sl.Q3;

public class Circle implements Figure{
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }

    @Override
    public void move() {
        System.out.println("Circle.move()");
    }

    @Override
    public void resize() {
        System.out.println("Circle.resize()");
    }

    @Override
    public void rotate() {
        System.out.println("Circle.rotate()");
    }

    @Override
    public void scale() {
        System.out.println("Circle.scale()");
    }

    @Override
    public void zoom() {
        System.out.println("Circle.zoom()");
    }
}
