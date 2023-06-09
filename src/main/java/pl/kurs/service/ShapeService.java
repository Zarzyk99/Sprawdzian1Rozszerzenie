package pl.kurs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import pl.kurs.models.Shape;

import java.io.*;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class ShapeService {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Shape findTheLargestArea(@NonNull List<Shape> shapes) {
        return shapes.stream()
                .max(Comparator.comparingDouble(Shape::getArea))
                .orElseThrow(NoSuchElementException::new);
    }

    public static Shape findTheLargestPerimeter(@NonNull List<Shape> shapes, Class<? extends Shape> shape) {
        return shapes.stream()
                .filter(s -> s.getClass().getSimpleName().equalsIgnoreCase(shape.getSimpleName()))
                .max(Comparator.comparingDouble(Shape::getPerimeter))
                .orElseThrow(NoSuchElementException::new);
    }

    @SneakyThrows
    public static void exportToJson(List<Shape> shapes, Path path) {
        Shape[] shapeArray = new Shape[shapes.size()];
        shapes.toArray(shapeArray);

        String json = mapper.writeValueAsString(shapeArray);
        File file = new File(path.toString());

        try (
                FileWriter fileWriter = new FileWriter(file)
        ) {
            fileWriter.write(json);
        }

    }

    public static List<Shape> importFromJson(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Invalid argument, path is null");
        }
        Shape[] shapes = new Shape[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            shapes = mapper.readValue(reader.lines().reduce(String::concat).orElse(""), shapes.getClass());
        }
        return List.of(shapes);
    }
}