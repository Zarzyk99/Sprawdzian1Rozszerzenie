package pl.kurs.json;

import com.google.gson.*;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

import java.lang.reflect.Type;

public class ShapeDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String shapeType = asJsonObject.get("type").getAsString();

        if (shapeType == null || shapeType.isBlank()) return null;

        return switch (shapeType) {
            case "circle" -> jsonDeserializationContext.deserialize(jsonElement, Circle.class);
            case "square" -> jsonDeserializationContext.deserialize(jsonElement, Square.class);
            case "rectangle" -> jsonDeserializationContext.deserialize(jsonElement, Rectangle.class);
            default -> null;
        };
    }

}
