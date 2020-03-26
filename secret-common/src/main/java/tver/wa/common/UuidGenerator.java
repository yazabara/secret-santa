package tver.wa.common;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator {

    public UUID generate() {
        return UUID.randomUUID();
    }
}
