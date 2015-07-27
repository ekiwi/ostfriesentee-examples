# Enums App
Currently Darjeeling does not support the built in Java Enum type.
This Application tries to investigate ways to mitigate this.

## ColorStaticClass
This class basically implements a subset of the Java Enums by hand.
For each value a `static final` Singleton is created which holds the
underlying value. Unlike with the Java Enums, no string representation
is provided. This saves memory.

## ColorConstants
Simple integer constants.
