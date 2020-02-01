package tver.wa.model.secret.santa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tver.wa.services.security.ClientRoles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Document(value = "Client")
@Builder(toBuilder = true)
public class Client {

    /**
     * Unique client uuid
     */
    @Id
    @NotNull(message = "Client UUID can't be empty")
    private UUID uuid;
    /**
     * Just client name
     */
    @NotBlank(message = "Client's name is necessary")
    private String name;
    /**
     * Client's public key - need to indicate client (each request)
     */
    @NotBlank(message = "Client's public key is necessary")
    private String publicKey;
    /**
     * Client's secret key - need to do something with Events
     */
    @NotBlank(message = "Client's secret key is necessary")
    private String secretKey;

    /**
     * Client roles.
     */
    private Set<ClientRoles> roles;
}
