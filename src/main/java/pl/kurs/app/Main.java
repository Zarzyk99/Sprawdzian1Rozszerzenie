package pl.kurs.app;

import lombok.SneakyThrows;
import pl.kurs.factory.ShapeFactory;
import pl.kurs.models.Shape;
import pl.kurs.models.Shapes;
import pl.kurs.service.ShapeService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Zaliczyłem test");

        exportTest();

        importTest();

    }

    @SneakyThrows
    private static void exportTest() {
        Path parentPath = Paths.get(Main.class.getResource("/").toURI()).getParent();
        Path path = Paths.get(parentPath + "/test.json");
        ShapeService.exportToJson(Shapes.of(getTestShapes()), path);
    }

    @SneakyThrows
    private static void importTest() {

        Path parentPath = Paths.get(Main.class.getResource("/").toURI()).getParent();
        Path path = Paths.get(parentPath + "/test.json");

        System.out.println(ShapeService.importFromJson(path));

    }

    private static List<Shape> getTestShapes() {
        List<Shape> shapes = new ArrayList<>();

        ShapeFactory shapeFactory = new ShapeFactory();
        shapes.add(shapeFactory.createSquare(5));
        shapes.add(shapeFactory.createSquare(8));
        shapes.add(shapeFactory.createRectangle(7, 3));
        shapes.add(shapeFactory.createRectangle(10, 5));
        shapes.add(shapeFactory.createCircle(7));
        shapes.add(shapeFactory.createCircle(3));
        return shapes;
    }
}

