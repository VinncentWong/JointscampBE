package centkarts.jointscamp.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity(name="likes")
@PrimaryKeyJoinColumns(
    value = [
        PrimaryKeyJoinColumn(name = "user", referencedColumnName = "user"),
        PrimaryKeyJoinColumn(name = "produk", referencedColumnName = "produk")
    ]
)
class Like(

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    var user: User,

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    var produk: Produk,

    @CreationTimestamp
    var createdAt: Date,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
){}