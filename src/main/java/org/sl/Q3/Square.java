package org.sl.Q3;

public class Square implements Figure{
    @Override
    public void draw() {
        System.out.println("Square.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Square.erase()");
    }

    @Override
    public void move() {
        System.out.println("Square.move()");
    }

    @Override
    public void resize() {
        System.out.println("Square.resize()");
    }

    @Override
    public void rotate() {
        System.out.println("Square.rotate()");
    }

    @Override
    public void scale() {
        System.out.println("Square.scale()");
    }

    @Override
    public void zoom() {
        System.out.println("Square.zoom()");
    }
}
