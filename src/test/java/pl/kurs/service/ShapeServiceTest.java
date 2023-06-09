package pl.kurs.service;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.kurs.factory.ShapeFactory;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    public void shouldThrowExceptionsWhenTheListIsNull() {
        final List<Shape> shapes = null;
        Assertions.assertThrows(NullPointerException.class, () -> ShapeService.findTheLargestArea(shapes));
    }

    @Test
    public void shouldThrowExceptionsWhenTheListIsEmpty() {
        final List<Shape> emptyShapes = new ArrayList<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> ShapeService.findTheLargestArea(emptyShapes));
    }


    @Test
    public void shouldFindLargestShapeByPerimeter() {
        Shape theLargestPerimeterShape = ShapeService.findTheLargestPerimeter(shapes, Square.class);

        Shape expectedShape = shapes.get(1);

        Assertions.assertEquals(expectedShape, theLargestPerimeterShape);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPathIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ShapeService.importFromJson(null));
    }

    @Test
    @SneakyThrows
    public void testExportToJson() {
        Path testFile = Files.createTempFile("shapes", ".json");
        ShapeService.exportToJson(shapes, testFile);

        String expectedJson = "[{\"type\":\"square\",\"side\":5.0},{\"type\":\"square\",\"side\":8.0}," +
                "{\"type\":\"rectangle\",\"length\":7.0,\"width\":3.0}," +
                "{\"type\":\"rectangle\",\"length\":10.0,\"width\":5.0}," +
                "{\"type\":\"circle\",\"radius\":7.0},{\"type\":\"circle\",\"radius\":3.0}]";
        String actualJson = Files.readString(testFile);

        Assertions.assertEquals(expectedJson, actualJson);

        Files.deleteIfExists(testFile);
    }

    @Test
    @SneakyThrows
    public void testImportFormJson() {
        Path testFile = Files.createTempFile("shapes", ".json");
        String testShapesJson = "[{\"type\":\"square\",\"side\":5.0},{\"type\":\"square\",\"side\":8.0}," +
                "{\"type\":\"rectangle\",\"length\":7.0,\"width\":3.0}," +
                "{\"type\":\"rectangle\",\"length\":10.0,\"width\":5.0}," +
                "{\"type\":\"circle\",\"radius\":7.0},{\"type\":\"circle\",\"radius\":3.0}]";

        Files.writeString(testFile, testShapesJson);
        var importedShapes = ShapeService.importFromJson(testFile);
        importedShapes.forEach((shape) -> Assertions.assertTrue(shapes.stream()
                .anyMatch(originalShape -> Objects.equals(originalShape, shape))));
    }


}