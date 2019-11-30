package khannanov.kvstorage.web.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
public class Pair {
    private final String value;
    private final Date created;
}
