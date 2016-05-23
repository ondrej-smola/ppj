package javappj;
import javax.persistence.*;
/**
 * Created by The CAT on 23.5.2016.
 */

@Entity
@Table(name = "coments")
public class Image {
    @Id
    @Column(name = "img_id")
    private int img_id;
}
