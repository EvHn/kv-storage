package khannanov.kvstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(appliesTo = "kvs.entry_changes")
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
    private String created;
}
