package tver.wa.model.secret.santa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(value = "User")
@Builder(toBuilder=true)
public class User {

    @Id
    private UUID uuid;
    private String name;
}
