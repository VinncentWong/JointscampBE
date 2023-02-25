package jointscamp.be.app.like;

import jointscamp.be.entity.Like;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query(nativeQuery = true, value =
            "SELECT " +
                    "count(user_id) FROM " +
                    "jointscamp.public.likes " +
                    "l GROUP BY produk_id " +
                    "HAVING produk_id = ?1"
    )
    public Long getProdukLike(UUID idProduk);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM likes WHERE produk_id = ?1 AND user_id = ?2")
    public void deleteProdukLike(UUID idProduk, Long idUser);

    @Query(nativeQuery = true, value = "SELECT user_id FROM likes WHERE produk_id = ?1")
    public List<Long> getUserLike(UUID produkId);

    @Query(nativeQuery = true, value = "SELECT produk_id FROM likes WHERE user_id = ?1")
    public List<String> getListProductLike(Long id);
}
