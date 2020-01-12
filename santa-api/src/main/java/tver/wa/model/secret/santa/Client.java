package tver.wa.model.secret.santa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(value = "Client")
@Builder(toBuilder = true)
public class Client {

    /**
     * Unique client uuid
     */
    @Id
    private UUID uuid;
    /**
     * Just client name
     */
    private String name;
    /**
     * Client's public key - need to indicate client (each request)
     */
    private String publicKey;
    /**
     * Client's secret key - need to do something with Events
     */
    private String secretKey;
    //TODO add ROLES (security)
}
