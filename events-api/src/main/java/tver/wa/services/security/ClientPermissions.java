package tver.wa.services.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientPermissions {

    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    EVENT_READ("event:write"),
    EVENT_WRITE("event:write");

    private final String permission;
}
