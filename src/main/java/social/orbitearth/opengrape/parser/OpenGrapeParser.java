package social.orbitearth.opengrape.parser;

import social.orbitearth.opengrape.OpenGrapeMetadata;

import java.util.Map;

public interface OpenGrapeParser {
    Map<OpenGrapeMetadata, String> parse(String htmlString);
}
