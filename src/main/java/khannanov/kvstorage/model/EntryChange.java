package khannanov.kvstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "entry_changes")
public class EntryChange {
    public static String ID = "id";
    public static String KEY = "key";
    public static String VALUE = "value";
    public static String CREATED = "created";
    @GeneratedValue
    @Id
    private int id;
    @NonNull
    private String key;
    @NonNull
    private String value;
    private Date created;
}
