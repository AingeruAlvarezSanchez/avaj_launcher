package io.github.aalvarez.avaj.launcher.exception;

import java.util.IllegalFormatException;
import java.util.Map;

import static io.github.aalvarez.avaj.launcher.exception.WeatherException.DefaultMessages.UNKNOWN_ERROR_DEFAULT_MSG;

public class WeatherException extends RuntimeException {
    public enum
    DefaultMessages {
        ILLEGAL_FORMAT_EXCEPTION_DEFAULT_MSG("Illegal format while registering aircraft to weather tower"),
        ILLEGAL_ARGUMENT_EXCEPTION_DEFAULT_MSG("Illegal argument while trying to register or unregister an aircraft to weather tower"),
        ILLEGAL_STATE_EXCEPTION_DEFAULT_MSG("Illegal state while retrieving current weather from weather tower"),
        UNKNOWN_ERROR_DEFAULT_MSG("Unknown error while registering aircraft to weather tower");

        private final String value;

        DefaultMessages(String value) {
            this.value = value;
        }
    }

    private static final Map<String, String> exceptionMessagesMap = Map.of(
            IllegalFormatException.class.getSimpleName(), DefaultMessages.ILLEGAL_FORMAT_EXCEPTION_DEFAULT_MSG.value,
            IllegalArgumentException.class.getSimpleName(), DefaultMessages.ILLEGAL_ARGUMENT_EXCEPTION_DEFAULT_MSG.value
    );

    public WeatherException(String exceptionName) {
        super(exceptionMessagesMap.getOrDefault(exceptionName, UNKNOWN_ERROR_DEFAULT_MSG.value));
    }

    public WeatherException(Throwable cause, String exceptionName) {
        super(exceptionMessagesMap.getOrDefault(exceptionName, UNKNOWN_ERROR_DEFAULT_MSG.value), cause);
    }
}
