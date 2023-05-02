package pl.kurs.models;


import java.util.Objects;

public class Rectangle extends Shape {
    private double a;
    private double b;

    private Rectangle(double a, double b) {
        super("rectangle");
        this.a = a;
        this.b = b;
    }

    public static Rectangle createRectangle(double length, double width) {
        return new Rectangle(length, width);
    }

    public double getLength() {
        return a;
    }

    public double getWidth() {
        return b;
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.a, a) == 0 && Double.compare(rectangle.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
