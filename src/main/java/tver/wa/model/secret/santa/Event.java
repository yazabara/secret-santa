package tver.wa.model.secret.santa;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Event {

    private UUID uuid;
    private User owner;
    private String ownerToken;
    private List<User> participants;
}
