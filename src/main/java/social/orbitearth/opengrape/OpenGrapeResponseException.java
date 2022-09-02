package social.orbitearth.opengrape;

public class OpenGrapeResponseException extends Exception {
    public OpenGrapeResponseException() {
    }

    public OpenGrapeResponseException(String message) {
        super(message);
    }

    public static class UnexpectedStatusCode extends OpenGrapeResponseException {
        public UnexpectedStatusCode(int statusCode) {
            super(String.valueOf(statusCode));
        }
    }
}
