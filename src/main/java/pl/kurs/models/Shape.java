package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class Shape {
    @JsonIgnore
    public abstract double getArea();

    @JsonIgnore
    public abstract double getPerimeter();

}
