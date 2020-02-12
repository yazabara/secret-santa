package tver.wa.services.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum ClientRoles {

    ADMIN(new HashSet<>(
            Arrays.asList(
                    ClientPermissions.CLIENT_READ,
                    ClientPermissions.CLIENT_WRITE
            )
    )),
    CLIENT(new HashSet<>(
            Arrays.asList(
                    ClientPermissions.EVENT_READ,
                    ClientPermissions.EVENT_WRITE)
    ));

    private final Set<ClientPermissions> permissions;
}
