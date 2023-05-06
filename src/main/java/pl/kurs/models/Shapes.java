package pl.kurs.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.MODULE)
public class Shapes {
    private List<Shape> shapes;


}
