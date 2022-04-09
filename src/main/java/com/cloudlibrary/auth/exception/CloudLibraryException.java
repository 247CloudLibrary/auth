package com.cloudlibrary.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CloudLibraryException extends RuntimeException {
    private final HttpStatus status;
    private final String type;

    public CloudLibraryException(MessageType message) {
        super(message.getMessage());
        this.status = message.getStatus();
        this.type = message.name();
    }
}
