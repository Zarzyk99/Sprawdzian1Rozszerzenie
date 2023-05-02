package pl.kurs.factory;

import pl.kurs.models.Circle;
import pl.kurs.models.Pair;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShapeFactory {
    private final Map<Double, Circle> circleCache = new HashMap<>();
    private final Map<Pair<Double, Double>, Rectangle> rectangleCache = new HashMap<>();
    private final Map<Double, Square> squareCache = new HashMap<>();


    public Optional<Circle> findCircle(double radius) {
        return Optional.ofNullable(circleCache.get(radius));
    }

    public Optional<Square> findSquare(double length) {
        return Optional.ofNullable(squareCache.get(length));
    }

    public Optional<Rectangle> findRectangle(double length, double width) {
        return Optional.ofNullable(rectangleCache.get(new Pair<>(length, width)));
    }

    public Circle createCircle(double radius) {
        return circleCache.computeIfAbsent(radius, Circle::createCircle);
    }

    public Rectangle createRectangle(double length, double width) {
        return rectangleCache.computeIfAbsent(new Pair<>(length, width),
                p -> Rectangle.createRectangle(p.getFirst(), p.getSecond()));
    }

    public Square createSquare(double length) {
        return squareCache.computeIfAbsent(length, Square::createSquare);

    }

}

