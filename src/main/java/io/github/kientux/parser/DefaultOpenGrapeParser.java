package io.github.kientux.parser;

import io.github.kientux.OpenGrapeMetadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultOpenGrapeParser implements OpenGrapeParser {
    @Override
    public Map<OpenGrapeMetadata, String> parse(String htmlString) {
        // extract meta tag
        Pattern metatagPattern = Pattern.compile("<meta(?:\".*?\"|'.*?'|[^'\"])*?>", Pattern.DOTALL);
        Matcher metatagMatcher = metatagPattern.matcher(htmlString);
        boolean found = metatagMatcher.find();
        if (!found) {
            return Collections.emptyMap();
        }

        metatagMatcher.reset();

        // prepare regular expressions to extract og property and content.
        Pattern propertyPattern = Pattern.compile("\\sproperty=[\"']*og:([a-zA_Z:]+)[\"']*");
        Pattern contentPattern = Pattern.compile("\\scontent=\\\\*?\"(.*?)\\\\*?\"");

        // create attribute dictionary
        Map<OpenGrapeMetadata, String> attributes = new HashMap<>();
        while (metatagMatcher.find()) {
            String metatag = metatagMatcher.group();
            Matcher propertyMatcher = propertyPattern.matcher(metatag);
            if (!propertyMatcher.find()) {
                continue;
            }
            Matcher contentMatcher = contentPattern.matcher(metatag);
            if (!contentMatcher.find()) {
                contentPattern = Pattern.compile("\\scontent=\\\\*?'(.*?)\\\\*?'");
                contentMatcher = contentPattern.matcher(metatag);

                if (!contentMatcher.find()) {
                    continue;
                }
            }

            String property = propertyMatcher.group(1);
            String content = contentMatcher.group(1);

            OpenGrapeMetadata metadata = OpenGrapeMetadata.withValue(property);
            if (metadata != null) {
                attributes.put(metadata, content);
            } else {
                System.err.println("Invalid OG property: " + property);
            }
        }

        return attributes;
    }
}
