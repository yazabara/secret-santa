package tver.wa.model.secret.santa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document("Event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    private UUID uuid;
    private User owner;
    private String ownerToken;
    private List<User> participants = new ArrayList<>();
}
