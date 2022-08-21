## OpenGrape is a Java wrapper for OGP (Open Graph protocol)

This is Java version of the Swift version here: https://github.com/satoshi-takano/OpenGraph

### Installation
```
<dependency>
    <groupId>io.github.kientux</groupId>
    <artifactId>opengrape</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Usage:

```java
String url = "https://opengraphprotocol.org/";

try {
    OpenGrape og = OpenGrape.fetch(url);
    // or `OpenGrape.fetch(url, "my custom user-agent")`
    String title = og.getValue(OpenGrapeMetadata.TITLE);
    System.out.println(title);
} catch (IOException | OpenGrapeResponseException e) {
    e.printStackTrace();
}
```