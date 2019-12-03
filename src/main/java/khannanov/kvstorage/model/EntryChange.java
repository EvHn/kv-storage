package khannanov.kvstorage.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "entry_changes")
public class EntryChange {
    public static String ID = "id";
    public static String KEY = "key";
    public static String VALUE = "value";
    public static String CREATED = "created";
    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    @ManyToOne
    private Entry entry;
    @NonNull
    private String value;
    private Date created;
}
