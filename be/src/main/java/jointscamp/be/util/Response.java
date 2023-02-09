package jointscamp.be.util;

import lombok.Data;

@Data
public class Response {

    private boolean success;

    private Object message;

    private Object data;
}
