package khannanov.kvstorage.service.model;

import khannanov.kvstorage.web.model.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryHistory {
    private String key;
    private List<Pair> values;
}
