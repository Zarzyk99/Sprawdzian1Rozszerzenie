package pl.kurs.factory;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

import java.util.Optional;


public class ShapeFactoryTest {

    private final ShapeFactory shapeFactory = new ShapeFactory();

    @Test
    public void shouldCreateCircleAndPutItInCache() {
        var circle = shapeFactory.createCircle(5d);
        Optional<Circle> cacheCircle = shapeFactory.findCircle(5d);

        Assertions.assertFalse(cacheCircle.isEmpty());
        Assertions.assertEquals(circle, cacheCircle.get());

        var secondCircle = shapeFactory.createCircle(5d);

        Assertions.assertEquals(circle, secondCircle);
    }

    @Test
    public void shouldCreateSquareAndPutItInCache() {
        var square = shapeFactory.createSquare(5d);
        Optional<Square> cacheSquare = shapeFactory.findSquare(5d);

        Assertions.assertFalse(cacheSquare.isEmpty());
        Assertions.assertEquals(square, cacheSquare.get());

        var secondSquare = shapeFactory.createSquare(5d);

        Assertions.assertEquals(square, secondSquare);
    }

    @Test
    public void shouldCreateRectangleAndPutItInCache() {
        var rectangle = shapeFactory.createRectangle(5d, 10d);
        Optional<Rectangle> cacheRectangle = shapeFactory.findRectangle(5d, 10d);

        Assertions.assertFalse(cacheRectangle.isEmpty());
        Assertions.assertEquals(rectangle, cacheRectangle.get());

        var secondRectangle = shapeFactory.createRectangle(5d, 10d);

        Assertions.assertEquals(rectangle, secondRectangle);
    }

}