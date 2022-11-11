package io.github.kientux;

import io.github.kientux.parser.DefaultOpenGrapeParser;
import io.github.kientux.parser.OpenGrapeParser;

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

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_8_4) AppleWebKit/5351 (KHTML, like Gecko) Chrome/36.0.869.0 Mobile Safari/5351";

    public OpenGrape(String htmlString, OpenGrapeParser parser) {
        this.source = parser.parse(htmlString);
    }

    public static OpenGrape fetch(String url) throws IOException, OpenGrapeResponseException {
        return fetch(url, DEFAULT_USER_AGENT);
    }

    public static OpenGrape fetch(String url, String userAgent) throws IOException, OpenGrapeResponseException {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        // some URLs need User-Agent header or else HTML source won't contain any OG tags
        connection.setRequestProperty("User-Agent", userAgent);
        int statusCode = connection.getResponseCode();
        if (statusCode < 200 || statusCode > 300) {
            throw new OpenGrapeResponseException.UnexpectedStatusCode();
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        StringBuilder htmlString = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            htmlString.append(line).append("\n");
        }
        return new OpenGrape(htmlString.toString(), new DefaultOpenGrapeParser());
    }

    public String getValue(OpenGrapeMetadata metadata) {
        return source.get(metadata);
    }
}
