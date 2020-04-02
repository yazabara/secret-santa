package tver.wa.client.exceptions;

import tver.wa.common.exceptions.SecretSantaException;

public class ClientNotFoundException extends SecretSantaException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
