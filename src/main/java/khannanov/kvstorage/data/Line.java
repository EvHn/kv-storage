package khannanov.kvstorage.data;

import lombok.Data;

@Data
public class Line {
    private static Integer counter = 0;
    private final Integer number;
    private final String string;
    private Line(String string) {
        number = counter;
        this.string = string;
        counter++;
    }

    public static synchronized Line createLine(String string) {
        return new Line(string);
    }
}
