package tver.wa.routes.v1.event;

import org.springframework.web.reactive.function.server.ServerRequest;
import tver.wa.exceptions.IncorrectTokenException;
import tver.wa.model.secret.santa.Event;

import java.util.function.Function;

class EventRequestValidator implements Function<Event, Event> {

    private final String tokenToCheck;

    EventRequestValidator(ServerRequest serverRequest) {
        this.tokenToCheck = serverRequest.headers().asHttpHeaders().getFirst("Event-Owner-Token");
    }

    @Override
    public Event apply(Event event) {
        if (event.getOwnerToken().equals(tokenToCheck)) {
            return event;
        } else {
            throw new IncorrectTokenException("Wrong token to access to this Event");
        }
    }
}
