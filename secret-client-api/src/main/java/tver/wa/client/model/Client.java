package tver.wa.client.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
}
