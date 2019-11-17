package khannanov.kvstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(appliesTo = "entries")
public class Entry {
    public static String KEY = "key";
    public static String VALUE = "value";
    public static String CHANGED = "changed";
    @Id
    private String key;
    private String value;
    private String changed;
}
