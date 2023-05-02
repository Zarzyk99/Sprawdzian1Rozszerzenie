package pl.kurs.models;

public abstract class Shape {


    protected Shape(String type) {
        this.type = type;
    }

    public Shape() {
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    protected String type;
}
