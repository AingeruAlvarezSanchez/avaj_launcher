package io.github.aalvarez.avaj.launcher.exception;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.NoSuchElementException;

public class ScenarioFileException extends RuntimeException {
    public enum
    DefaultMessages {
        IO_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG("IO Error while trying to recover file content"),
        NO_SUCH_FILE_EXCEPTION_DEFAULT_MSG("The scenario file does not exist"),
        ILLEGAL_ARGUMENT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG("Illegal argument while reading scenario file"),
        NO_SUCH_ELEMENT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG("First line of file was found empty while reading scenario file"),
        NUMBER_FORMAT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG("Error while parsing a number from scenario file"),
        UNKNOWN_ERROR_DEFAULT_MSG("Unknown error while reading scenario file");

        private final String value;

        DefaultMessages(String value) {
            this.value = value;
        }
    }

    private static final Map<String, String> exceptionMessagesMap = Map.of(
            IOException.class.getSimpleName(), DefaultMessages.IO_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG.value,
            NoSuchFileException.class.getSimpleName(), DefaultMessages.NO_SUCH_FILE_EXCEPTION_DEFAULT_MSG.value,
            IllegalArgumentException.class.getSimpleName(), DefaultMessages.ILLEGAL_ARGUMENT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG.value,
            NoSuchElementException.class.getSimpleName(), DefaultMessages.NO_SUCH_ELEMENT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG.value,
            NumberFormatException.class.getSimpleName(), DefaultMessages.NUMBER_FORMAT_SCENARIO_FILE_EXCEPTION_DEFAULT_MSG.value
    );

    public ScenarioFileException(Throwable cause, String exceptionName) {
        super(exceptionMessagesMap.getOrDefault(exceptionName, DefaultMessages.UNKNOWN_ERROR_DEFAULT_MSG.value), cause);
    }
}
