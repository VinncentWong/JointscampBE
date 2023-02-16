package jointscamp.be.mapper.produk;

import jointscamp.be.dto.produk.UpdateProdukDto;
import jointscamp.be.entity.Produk;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdukMapper {

    public final ProdukMapper INSTANCE = Mappers.getMapper(ProdukMapper.class);

    @Mapping(target = "produk.namaProduk", source = "dto.produkName")
    @Mapping(target = "produk.lokasiProduk", source = "dto.produkLocation")
    @Mapping(target = "produk.detailProduk", source = "dto.produkDetail")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Produk updateProduk(UpdateProdukDto dto, @MappingTarget Produk produk);
}
