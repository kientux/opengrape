package org.tux;

import org.tux.parser.DefaultOpenGrapeParser;
import org.tux.parser.OpenGrapeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenGrape {
    private final Map<OpenGrapeMetadata, String> source;

    public OpenGrape(String htmlString, OpenGrapeParser parser) {
        this.source = parser.parse(htmlString);
    }

    public static OpenGrape fetch(String url) throws IOException, OpenGrapeResponseException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        int statusCode = connection.getResponseCode();
        if (statusCode < 200 || statusCode > 300) {
            throw new OpenGrapeResponseException.UnexpectedStatusCode();
        }
        InputStream inputStream = connection.getInputStream();
        String htmlString = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return new OpenGrape(htmlString, new DefaultOpenGrapeParser());
    }

    public String getValue(OpenGrapeMetadata metadata) {
        return source.get(metadata);
    }
}
