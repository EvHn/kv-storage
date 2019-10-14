package khannanov.kvstorage.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class State {
    private final List<Line> state;

    public void set(Integer number, Line line) {
        if(!line.equals(state.get(number))) {
            state.set(number, line);

        }
    }

    public void insert(Integer afterLine, Line line) {
        state.add(afterLine, line);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        state.forEach(line -> stringBuilder.append(line.getString()));
        return stringBuilder.toString();
    }
}
