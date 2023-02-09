package jointscamp.be.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    public ResponseEntity<Response> sendResponse(HttpStatus status, boolean success, Object message, Object data){
        Response response = new Response();
        response.setMessage(message);
        response.setSuccess(success);
        response.setData(data);
        return ResponseEntity.status(status.value()).body(response);
    }
}
