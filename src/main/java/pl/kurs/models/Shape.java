package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@NoArgsConstructor
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @Type(value = Square.class, name = "square"),
        @Type(value = Rectangle.class, name = "rectangle"),
        @Type(value = Circle.class, name = "circle"),
})
public abstract class Shape {
    @JsonIgnore
    public abstract double getArea();

    @JsonIgnore
    public abstract double getPerimeter();

}
