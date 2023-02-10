package jointscamp.be.entity;

import jakarta.persistence.*;
import jointscamp.be.util.Extractable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicUpdate
@DynamicInsert
public class User implements Extractable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Lob
    private byte[] photoProfile;

    private String whatsappContact;

    private String lineContact;

    private String instagramContact;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Produk> produks;
}
