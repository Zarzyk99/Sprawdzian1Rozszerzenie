package pl.kurs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.kurs.factory.ShapeFactory;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;

import java.util.ArrayList;
import java.util.List;

public class ShapeServiceTest {
    private final List<Shape> shapes = new ArrayList<>();
    private final ShapeFactory shapeFactory = new ShapeFactory();


    @Before
    public void setUp() {
        shapes.add(shapeFactory.createSquare(5));
        shapes.add(shapeFactory.createSquare(8));
        shapes.add(shapeFactory.createRectangle(7, 3));
        shapes.add(shapeFactory.createRectangle(10, 5));
        shapes.add(shapeFactory.createCircle(7));
        shapes.add(shapeFactory.createCircle(3));

    }

    @Test
    public void ShouldFindTheLargestShape() {
        Shape theLargestAreaShape = ShapeService.findTheLargestArea(shapes);

        Shape shapeWithTheLargestArea = shapes.get(4);

        Assertions.assertEquals(shapeWithTheLargestArea, theLargestAreaShape);
    }

    @Test
    public void shouldFindLargestShapeByPerimeter() {
        Shape theLargestPerimeterShape = ShapeService.findTheLargestPerimeter(shapes, Square.class);

        Shape expectedShape = shapes.get(1);

        Assertions.assertEquals(expectedShape, theLargestPerimeterShape);
    }

}