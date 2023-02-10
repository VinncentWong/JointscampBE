package jointscamp.be.dto.produk;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jointscamp.be.entity.Produk;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public record CreateProdukDto(
        @NotNull(message = "product name should not be null")
        @NotBlank(message = "product name should not blank")
        @Length(min = 4, message = "product name length should be >= 4")
        String produkName,

        @NotNull(message = "product detail should not be null")
        @NotBlank(message = "product detail should not be blank")
        @Length(min = 4, message = "product detail length should be >= 4")
        String produkDetail,

        @NotNull(message = "product location should not be null")
        @NotBlank(message = "product location should not be blank")
        @Length(min = 4, message = "product location length should be >= 4")
        String produkLocation
) {

    public Produk transformIntoProduk() throws IOException {
        return Produk
                .builder()
                .namaProduk(this.produkName)
                .detailProduk(this.produkDetail)
                .lokasiProduk(this.produkLocation)
                .build();
    }
}
