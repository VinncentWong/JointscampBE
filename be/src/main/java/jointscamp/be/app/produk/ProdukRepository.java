package jointscamp.be.app.produk;

import jointscamp.be.entity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM produk WHERE user_id = ?1")
    List<Produk> getProdukByUserId(Long userId);
}
