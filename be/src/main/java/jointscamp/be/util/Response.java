package jointscamp.be.util;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

    private boolean success;

    private Object message;

    private Object data;
}
