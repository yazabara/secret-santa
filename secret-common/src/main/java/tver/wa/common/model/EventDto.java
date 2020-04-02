package tver.wa.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class EventDto {

    /**
     * Unique uuid for event (can't be changed by edit operation)
     */
    private UUID uuid;
    /**
     * Creator client uuid (can't be changed by edit operation)
     */
    private UUID creator;
    /**
     * Event title
     */
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
