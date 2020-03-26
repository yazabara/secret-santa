package tver.wa.events.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
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
     * Creator client uuid (can't be changed by edit operation)
     */
    @NotEmpty
    private UUID creator;
    /**
     * Event title
     */
    @NotBlank
    private String title;
    /**
     * Event description
     */
    private String description;
    /**
     * Start event date
     */
    private Date start;
    /**
     * End event date
     */
    private Date end;
}
