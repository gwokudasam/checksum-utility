#Checksum Generator Utility

#### First: Build & Test Utility
Now it is the time to build our **checksum utility/microservice** and run it by running the following commands:

```bash
samsoftx@env360 ~/hmac-checksum-utility 
λ ./mvnw clean verify -Ddockerfile.skip
```

All build commands and test suite should run successfully, and the final output should be like this:

```bash

[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ hmac-checksum-util ---
[INFO] Building jar: ~/hmac-checksum-utility/target/hmac-checksum-util-1.0.2-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.828 s
[INFO] Finished at: 2020-12-13T15:25:21+02:00
[INFO] ------------------------------------------------------------------------

# License
Copyright (C) 2020-2021 Samuel Gwokuda, Licensed under the MIT License.