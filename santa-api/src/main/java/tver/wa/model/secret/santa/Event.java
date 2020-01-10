package tver.wa.model.secret.santa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document("Event")
@Builder(toBuilder = true)
public class Event {

    /**
     * Unique uuid for event (can't be changed by edit operation)
     */
    @Id
    private UUID uuid;
    /**
     * Creator user uuid (can't be changed by edit operation)
     */
    private UUID creator;
    /**
     * Crud Operations token - possibility to do something with Event (CRUD operations)
     */
    private String ownerToken;
    /**
     * Start event date
     */
    private Date start;
    /**
     * End event date
     */
    private Date end;
    /**
     * Event description
     */
    private String description;
    /**
     * Event participants
     */
    private List<UUID> participants;
}
