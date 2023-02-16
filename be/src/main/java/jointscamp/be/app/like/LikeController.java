package jointscamp.be.app.like;

import jointscamp.be.exception.user.UserNotFoundException;
import jointscamp.be.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService service;

    @Autowired
    public LikeController(LikeService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createLike(
            @RequestParam("userId")
            Long userId,
            @RequestParam("produkId")
            String produkId
    ){
        UUID uuid = UUID.fromString(produkId);
        return this.service.addLike(userId, uuid);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getProdukLike(@PathVariable("id") String productId){
        UUID uuid = UUID.fromString(productId);
        return this.service.getProdukLike(uuid);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteProdukLike(
            @RequestParam("userId")
            Long userId,
            @RequestParam("produkId")
            String produkId
    ){
        UUID uuid = UUID.fromString(produkId);
        return this.service.deleteProdukLike(uuid, userId);
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<Response> getListUserWhoLike(@PathVariable("id") String produkId) throws UserNotFoundException {
        UUID uuid = UUID.fromString(produkId);
        return this.service.getListUserWhoLike(uuid);
    }
}
