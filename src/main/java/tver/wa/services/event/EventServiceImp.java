package tver.wa.services.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tver.wa.repositories.event.EventRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
}
