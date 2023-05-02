package pl.kurs.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.kurs.exception.InvalidNumberException;
import pl.kurs.files.FileUtil;
import pl.kurs.json.ShapeDeserializer;
import pl.kurs.models.Shape;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class ShapeService {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Shape.class, new ShapeDeserializer())
            .create();

    public static Shape findTheLargestArea(List<Shape> shapes) {
        return shapes.stream()
                .max(Comparator.comparingDouble(Shape::getArea))
                .orElseThrow(NoSuchElementException::new);
    }

    public static Shape findTheLargestPerimeter(List<Shape> shapes, Class<? extends Shape> shape) {
        return shapes.stream()
                .filter(s -> s.getClass().getSimpleName().equalsIgnoreCase(shape.getSimpleName()))
                .max(Comparator.comparingDouble(Shape::getPerimeter))
                .orElseThrow(NoSuchElementException::new);
    }

    public static void exportToJson(List<Shape> shapes, Path path) throws IOException {
        String json = gson.toJson(shapes);
        File file = FileUtil.createFile(path.toString());
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(json);
        fileWriter.close();
    }

    public static List<Shape> importFromJson(Path path) throws IOException {
        if (path == null) {
            throw new InvalidNumberException("Invalid argument");
        }
        var shapes = new ArrayList<Shape>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            shapes = gson.fromJson(reader, new TypeToken<List<Shape>>() {
            }.getType());
        }
        return shapes;
    }
}


