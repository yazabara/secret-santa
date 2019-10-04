package tver.wa.routes.v1.event;

import lombok.RequiredArgsConstructor;
import tver.wa.exceptions.IncorrectTokenException;
import tver.wa.model.secret.santa.Event;

import java.util.function.Function;

@RequiredArgsConstructor
public class EventRequestValidator implements Function<Event, Event> {

    private final String tokenToCheck;


    @Override
    public Event apply(Event event) {
        if (event.getOwnerToken().equals(tokenToCheck)) {
            return event;
        } else {
            throw new IncorrectTokenException("Wrong token to access to this Event");
        }
    }
}
