package jointscamp.be.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity(name = "likes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Like {

    @EmbeddedId
    private LikeId id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

}
