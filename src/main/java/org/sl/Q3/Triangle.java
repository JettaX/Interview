package org.sl.Q3;

public class Triangle implements Figure {
    @Override
    public void draw() {
        System.out.println("Triangle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Triangle.erase()");
    }

    @Override
    public void move() {
        System.out.println("Triangle.move()");
    }

    @Override
    public void resize() {
        System.out.println("Triangle.resize()");
    }

    @Override
    public void rotate() {
        System.out.println("Triangle.rotate()");
    }

    @Override
    public void scale() {
        System.out.println("Triangle.scale()");
    }

    @Override
    public void zoom() {
        System.out.println("Triangle.zoom()");
    }
}
