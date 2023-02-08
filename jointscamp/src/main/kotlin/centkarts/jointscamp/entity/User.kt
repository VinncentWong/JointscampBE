package centkarts.jointscamp.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date

@Entity(name = "users")
class User(
    var email: String,
    var password: String,
    var photoProfile: String?,
    var whatsappContact: String?,
    var lineContact: String?,
    var instagramContact: String?,

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "user"
    )
    var products: List<Produk>,

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "user"
    )
    var likes: List<Like>,

    @CreationTimestamp
    var createdAt: Date,

    @UpdateTimestamp
    var updatedAt: Date,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
){}
