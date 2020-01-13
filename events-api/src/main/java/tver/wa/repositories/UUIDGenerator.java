package tver.wa.repositories;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator {

    public UUID generate() {
        return UUID.randomUUID();
    }
}
