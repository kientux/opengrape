package org.tux.parser;

import org.tux.OpenGrapeMetadata;

import java.util.Map;

public interface OpenGrapeParser {
    Map<OpenGrapeMetadata, String> parse(String htmlString);
}
