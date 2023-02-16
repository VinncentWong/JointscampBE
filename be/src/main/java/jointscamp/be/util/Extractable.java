package jointscamp.be.security.authentication.util;

import java.util.Date;

public interface Extractable {

    public Long getId();

    public String getUsername();

    public Date getCreatedAt();
}
