package ry.yegorr.audio_ms.exception;

import org.springframework.http.*;

public class ResourceNotFoundException extends ApplicationException {

    /**
     * @param message причина ошибки на сервере
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
