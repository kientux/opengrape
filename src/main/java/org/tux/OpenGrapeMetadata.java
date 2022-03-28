package org.tux;

@SuppressWarnings("unused")
public enum OpenGrapeMetadata {
    // Basic Metadata
    TITLE("title"), TYPE("type"), IMAGE("image"), URL("url"),

    // Optional Metadata
    AUDIO("audio"), DESCRIPTION("description"), DETERMINER("determiner"),
    LOCALE("locale"), LOCALE_ALTERNATE("locale:alternate"),
    SITE_NAME("site_name"), VIDEO("video"),

    // Structured Properties
    IMAGE_URL("image:url"), IMAGE_SECURE_URL("image:secure_url"),
    IMAGE_TYPE("image:type"),
    IMAGE_WIDTH("image:width"), IMAGE_HEIGHT("image:height"),

    // Music
    MUSIC_DURATION("music:duration"), MUSIC_ALBUM("music:album"),
    MUSIC_ALBUM_DISC("music:album:disc"), MUSIC_ALBUM_TRACK("music:album:track"),
    MUSIC_MUSICIAN("music:musician"), MUSIC_SONG("music:song"),
    MUSIC_SONG_DISC("music:song:disc"), MUSIC_SONG_TRACK("music:song:track"),
    MUSIC_RELEASE_DATE("music:release_date"), MUSIC_CREATOR("music:creator"),

    // Video
    VIDEO_ACTOR("video:actor"),
    VIDEO_ACTOR_ROLE("video:actor:role"),
    VIDEO_DIRECTOR("video:director"),
    VIDEO_WRITER("video:writer"),
    VIDEO_DURATION("video:duration"),
    VIDEO_RELEASE_DATE("video:release_date"),
    VIDEO_TAG("video:tag"),
    VIDEO_SERIES("video:series"),

    // No Vertical
    ARTICLE_PUBLISHED_TIME("article:published_time"),
    ARTICLE_MODIFIED_TIME("article:modified_time"),
    ARTICLE_EXPIRATION_TIME("article:expiration_time"),
    ARTICLE_AUTHOR("article:author"),
    ARTICLE_SECTION("article:section"),
    ARTICLE_TAG("article:tag"),

    BOOK_AUTHOR("book:author"),
    BOOK_ISBN("book:isbn"),
    BOOK_RELEASE_DATE("book:release_date"),
    BOOK_TAG("book:tag"),

    PROFILE_FIRST_NAME("profile:first_name"),
    PROFILE_LAST_NAME("profile:last_name"),
    PROFILE_USERNAME("profile:username"),
    PROFILE_GENDER("profile:gender");

    private final String value;

    OpenGrapeMetadata(String value) {
        this.value = value;
    }

    public static OpenGrapeMetadata withValue(String value) {
        for (OpenGrapeMetadata m : values()) {
            if (m.value.equals(value)) {
                return m;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
