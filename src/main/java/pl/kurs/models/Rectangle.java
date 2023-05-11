package pl.kurs.models;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.MODULE)
@Data
@JsonTypeName("rectangle")
public class Rectangle extends Shape {
    private double length;
    private double width;

    private Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public static Rectangle createRectangle(double length, double width) {
        return new Rectangle(length, width);
    }


    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }


    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.length, length) == 0 && Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}
