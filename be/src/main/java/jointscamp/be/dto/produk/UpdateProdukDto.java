package jointscamp.be.dto.produk;

import jointscamp.be.entity.Produk;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public record UpdateProdukDto(

        @Length(min = 4, message = "product name length should be >= 4")
        String produkName,

        @Length(min = 4, message = "product detail length should be >= 4")
        String produkDetail,

        @Length(min = 4, message = "product location length should be >= 4")
        String produkLocation
) {
        public void updateProduk(Produk produk) throws IOException {
                produk.setDetailProduk(this.produkDetail);
                produk.setLokasiProduk(this.produkLocation);
                produk.setNamaProduk(this.produkName);
        }
}
