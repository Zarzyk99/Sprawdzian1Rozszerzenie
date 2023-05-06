package pl.kurs.service;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.kurs.factory.ShapeFactory;
import pl.kurs.models.Shape;
import pl.kurs.models.Shapes;
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
        List<Shape> shapes = new ArrayList<>();
        shapes.add(shapeFactory.createCircle(5));
        shapes.add(shapeFactory.createRectangle(2, 3));

        Path testFile = Files.createTempFile("shapes", ".json");
        ShapeService.exportToJson(Shapes.of(shapes), testFile);

        String expectedJson = "[\"pl.kurs.models.Shapes\",{\"shapes\":[\"java.util.ArrayList\"," +
                "[[\"pl.kurs.models.Circle\",{\"r\":5.0}],[\"pl.kurs.models.Rectangle\",{\"a\":2.0,\"b\":3.0}]]]}]";
        String actualJson = Files.readString(testFile);

        Assertions.assertEquals(expectedJson, actualJson);

        Files.deleteIfExists(testFile);
    }

    @Test
    @SneakyThrows
    public void testImportFormJson() {
        Path testFile = Files.createTempFile("shapes", ".json");
        String testShapesJson = "[\"pl.kurs.models.Shapes\",{\"shapes\":[\"java.util.ArrayList\"," +
                "[[\"pl.kurs.models.Square\",{\"a\":5.0}],[\"pl.kurs.models.Square\",{\"a\":8.0}]," +
                "[\"pl.kurs.models.Rectangle\",{\"a\":7.0,\"b\":3.0}],[\"pl.kurs.models.Rectangle\"," +
                "{\"a\":10.0,\"b\":5.0}],[\"pl.kurs.models.Circle\",{\"r\":7.0}]," +
                "[\"pl.kurs.models.Circle\",{\"r\":3.0}]]]}]";

        Files.writeString(testFile, testShapesJson);
        var importedShapes = ShapeService.importFromJson(testFile);
        importedShapes.forEach((shape) -> Assertions.assertTrue(shapes.stream()
                .anyMatch(originalShape -> Objects.equals(originalShape, shape))));
    }


}