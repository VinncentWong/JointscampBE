package jointscamp.be.app.like;

import jakarta.transaction.Transactional;
import jointscamp.be.app.produk.ProdukRepository;
import jointscamp.be.app.user.UserRepository;
import jointscamp.be.entity.Like;
import jointscamp.be.entity.LikeId;
import jointscamp.be.entity.Produk;
import jointscamp.be.entity.User;
import jointscamp.be.exception.user.UserNotFoundException;
import jointscamp.be.util.Response;
import jointscamp.be.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LikeService {

    private final LikeRepository repository;

    private final UserRepository userRepository;

    private final ProdukRepository produkRepository;

    private final ResponseUtil responseUtil;

    @Autowired
    public LikeService(LikeRepository repository, ResponseUtil responseUtil, UserRepository userRepository, ProdukRepository produkRepository){
        this.repository = repository;
        this.responseUtil = responseUtil;
        this.userRepository = userRepository;
        this.produkRepository = produkRepository;
    }

    public ResponseEntity<Response> addLike(Long userId, UUID produkId){
        Like like = new Like();
        LikeId idLike = new LikeId();
        idLike.setProdukId(produkId);
        idLike.setUserId(userId);
        like.setId(idLike);
        var result = this.repository.save(like);
        return this.responseUtil.sendResponse(HttpStatus.CREATED, true, "success add like", result);
    }

    public ResponseEntity<Response> getProdukLike(UUID produkId){
        Long like = this.repository.getProdukLike(produkId);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get product like", like);
    }

    public ResponseEntity<Response> deleteProdukLike(UUID produkId, Long userId){
        this.repository.deleteProdukLike(produkId, userId);
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success delete product like", null);
    }

    public ResponseEntity<Response> getListUserWhoLike(UUID produkId) throws UserNotFoundException {
        List<Long> listUserId = this.repository.getUserLike(produkId);
        List<User> users = new ArrayList<>();
        try {
            listUserId.forEach((id) -> {
                try {
                    users.add(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("wrong user id")));
                } catch (UserNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch(RuntimeException exception){
            throw new UserNotFoundException(exception.getMessage());
        }
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get list users like", users);
    }

    public ResponseEntity<Response> getProductLikes(Long idUser){
        List<String> ids = this.repository.getListProductLike(idUser);
        List<Produk> produks = new ArrayList<>();
        ids.forEach((id) -> {
            Optional<Produk> produk = this.produkRepository.findById(UUID.fromString(id));
            produk.ifPresent(produks::add);
        });
        return this.responseUtil.sendResponse(HttpStatus.OK, true, "success get list like", produks);
    }
}
