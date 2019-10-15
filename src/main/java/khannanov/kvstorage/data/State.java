package khannanov.kvstorage.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class State {
    private final List<Line> lines;

    public static State copy(State state) {
        return new State(List.copyOf(state.lines));
    }

    public void set(Integer number, Line line) {
        if(!line.equals(lines.get(number))) {
            lines.set(number, line);

        }
    }

    public void insert(Integer afterLine, Line line) {
        lines.add(afterLine, line);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(line -> stringBuilder.append(line.getString()));
        return stringBuilder.toString();
    }
}
