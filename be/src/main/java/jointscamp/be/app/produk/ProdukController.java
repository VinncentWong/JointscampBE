package jointscamp.be.app.produk;

import jakarta.validation.Valid;
import jointscamp.be.dto.produk.CreateProdukDto;
import jointscamp.be.dto.produk.UpdateProdukDto;
import jointscamp.be.exception.UserNotFoundException;
import jointscamp.be.exception.produk.ProdukNotFoundException;
import jointscamp.be.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/produk")
public class ProdukController {

    private final  ProdukService service;

    @Autowired
    public ProdukController(ProdukService service){
        this.service = service;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Response> createProduk(
            @PathVariable("userId")
            Long userId,
            @Valid
            @RequestPart("data")
            CreateProdukDto dto,
            @RequestPart("picture")
            MultipartFile file) throws UserNotFoundException, IOException {
        return this.service.createProduk(userId, dto, file);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> getProduk(@PathVariable("userId") Long userId){
        return this.service.getProduks(userId);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Response> updateProduk(
            @PathVariable("id")
            String id,
            @Valid
            @RequestPart(value = "data", required = false)
            UpdateProdukDto dto,
            @RequestPart(value = "picture", required = false)
            MultipartFile file
    ) throws ProdukNotFoundException, IOException {
        UUID uuid = UUID.fromString(id);
     return this.service.updateProduk(uuid, dto, file);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduk(@PathVariable("id") String id){
        UUID uuid = UUID.fromString(id);
        return this.service.deleteProduk(uuid);
    }

}
