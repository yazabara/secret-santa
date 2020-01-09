package tver.wa.model.secret.santa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Document("Event")
@Builder(toBuilder=true)
public class Event {

    @Id
    private UUID uuid;
    private User owner;
    private String ownerToken;
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;
    private List<User> participants;
}
