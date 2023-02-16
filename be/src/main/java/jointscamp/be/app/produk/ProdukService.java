package jointscamp.be.app.produk;

import jakarta.transaction.Transactional;
import jointscamp.be.app.user.UserRepository;
import jointscamp.be.dto.produk.CreateProdukDto;
import jointscamp.be.dto.produk.UpdateProdukDto;
import jointscamp.be.entity.Produk;
import jointscamp.be.entity.User;
import jointscamp.be.exception.user.UserNotFoundException;
import jointscamp.be.exception.produk.ProdukNotFoundException;
import jointscamp.be.mapper.produk.ProdukMapper;
import jointscamp.be.util.Response;
import jointscamp.be.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProdukService {

    private final ProdukRepository produkRepository;

    private final UserRepository userRepository;

    private final ResponseUtil responseUtil;

    @Autowired
    public ProdukService(ProdukRepository produkRepository, ResponseUtil responseUtil, UserRepository userRepository){
        this.produkRepository = produkRepository;
        this.responseUtil = responseUtil;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Response> createProduk(Long userId, CreateProdukDto dto, MultipartFile file) throws IOException, UserNotFoundException {
        Produk produk = dto.transformIntoProduk();
        produk.setGambarProduk(file.getBytes());
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user doesn't exist"));
        produk.setUser(user);
        var result = this.produkRepository.save(produk);
        return this.responseUtil.sendResponse(HttpStatus.CREATED, true, "success create product", result);
    }

    public ResponseEntity<Response> getProduks(Long userId){
        List<Produk> produks = this.produkRepository.getProdukByUserId(userId);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get produks data", produks);
    }

    public ResponseEntity<Response> updateProduk(UUID produkId, UpdateProdukDto dto, MultipartFile file) throws ProdukNotFoundException, IOException {
        Produk produk = this.produkRepository.findById(produkId).orElseThrow(() -> new ProdukNotFoundException("product doesn't exist in database"));
        var result = ProdukMapper.INSTANCE.updateProduk(dto, produk);
        this.produkRepository.save(result);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success update produk", null);
    }

    public ResponseEntity<Response> deleteProduk(UUID produkId){
        this.produkRepository.deleteById(produkId);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success delete produk", null);
    }
}
