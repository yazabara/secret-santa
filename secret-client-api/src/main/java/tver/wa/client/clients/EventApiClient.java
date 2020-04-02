package tver.wa.client.clients;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;
import tver.wa.common.model.EventDto;

@ReactiveFeignClient(url = "localhost:10901/v1/events")
public interface EventApiClient {

    @PutMapping
    Mono<EventDto> createEvent(@RequestBody EventDto eventDto);

}
