package org.tux;

public class OpenGrapeResponseException extends Exception {
    public static class UnexpectedStatusCode extends OpenGrapeResponseException {
    }
}
