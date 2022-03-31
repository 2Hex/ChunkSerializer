# ChunkSerializer
[![](https://jitpack.io/v/2Hex/ChunkSerializer.svg)](https://jitpack.io/#2Hex/ChunkSerializer)
[![CodeFactor](https://www.codefactor.io/repository/github/2hex/chunkserializer/badge)](https://www.codefactor.io/repository/github/2hex/chunkserializer)

Serializes Chunks, Basically converting them to namespacedkeys that can be used later, not exactly serializing with its common definition.

**Note:**
- This will only work for 1.17.1+
- Only use classes ChunkSerializer, and classes inside api package.

Easily turn a Chunk into a namespacedkey of the name of your choice, then turn it back into an Chunk.

# Installation
**For MAVEN:**
Add this to your repositories
```HTML
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Then add this to your dependencies
 ```HTML
<dependency>
    <groupId>com.github.2Hex</groupId>
    <artifactId>ChunkSerializer</artifactId>
    <version>Tag</version>
</dependency>
```

**For GRADLE:**

Use this:

  ```HTML
	allprojects {
repositories {
...
maven { url 'https://jitpack.io' }
}
}
```
And the dependency:

```HTML
	dependencies {
implementation 'com.github.2Hex:ChunkSerializer:Tag'
}
```

# Usage

First, you have to create an object of ChunkSerializer in your onEnable:

```Java
ChunkSerializer entitySerializer = ChunkSerializer.create(this);
```

Then make a variable, referencing the class EntityHandler:

```Java
private static ChunkHandler handler;
```

then on your onEnable again:

```Java
handler = chunkSerializer.getChunkHandler();
```

Then make a static getter:

```Java
public static ChunkHandler getHandler() {
        return handler;
}
```
Then later in whatever class, you can use
```Java
	NamespacedKey namespacedkey = new NamespacedKey(pluginInstance, "your-key-here");
        YourMainClass.getHolder().serialize(chunk, namespackedkey);
        ChunkResult chunkDeserialized = YourMainClass.getHolder().deserialize(namespacedkey);
```
**Note that you should have a unique namespacedkey for each unique chunk serialization.**

You can invoke `spawnAndGet()` (which will return the chunk and spawn it) on the ChunkResult later, or `spawn`.


And to Destroy a serialization (Remove its access, and its place in server storage) Use:
```Java
YourMainClass.getHolder().destroy(namespacedkey)
```
