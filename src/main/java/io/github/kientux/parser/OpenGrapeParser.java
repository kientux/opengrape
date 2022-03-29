package io.github.kientux.parser;

import io.github.kientux.OpenGrapeMetadata;

import java.util.Map;

public interface OpenGrapeParser {
    Map<OpenGrapeMetadata, String> parse(String htmlString);
}
