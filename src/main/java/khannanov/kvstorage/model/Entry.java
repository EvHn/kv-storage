package khannanov.kvstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "entries")
public class Entry {
    public static String KEY = "key";
    public static String VALUE = "value";
    public static String CHANGED = "changed";
    @Id
    private String key;
    @ManyToOne
    private User user;
    @NonNull
    private String value;
    private Date changed;
}
