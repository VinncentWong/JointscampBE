package centkarts.jointscamp.entity

import com.fasterxml.jackson.annotation.JsonKey
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import java.util.UUID

@Entity
class Produk(

    var productName: String,
    var productDetail: String,
    var productImage: String?,
    var productLocation: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "produk")
    var like: List<Like>,

    @CreationTimestamp
    var createdAt: Date,

    @UpdateTimestamp
    var updatedAt: Date,

    var deletedAt: Date?,

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    var id: UUID?
){}