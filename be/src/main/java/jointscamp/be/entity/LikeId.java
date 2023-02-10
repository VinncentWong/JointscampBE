package jointscamp.be.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Setter
@Getter
public class LikeId implements Serializable {
    private Long userId;
    private UUID produkId;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LikeId id){
            return id.produkId.compareTo(this.produkId) == 0 && (this.userId.equals(id.userId));
        } else {
            return false;
        }
    }
}