package io.github.kientux;

public class OpenGrapeResponseException extends Exception {
    public static class UnexpectedStatusCode extends OpenGrapeResponseException {
    }
}
