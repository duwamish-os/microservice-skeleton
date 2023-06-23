microservice skeleton
-----------------------

This demonstrates how to create a microservice with api and schema as separate jars.

- microservice-api
- microservice-schema
 

publishing artifacts
----------

```bash
λ gradle test

BUILD SUCCESSFUL in 0s
5 actionable tasks: 5 up-to-date

λ gradle publishToMavenLocal
λ ls -l ~/.m2/repository/org/duwamish/microservice/
total 0
drwxr-xr-x  4 prayagupd  184630988  128 Jul 21 16:11 microservice-api
drwxr-xr-x  4 prayagupd  184630988  128 Jul 21 16:13 microservice-schema
```

creating fat artifact
---------

```bash
λ gradle fatJar
λ ls -l microservice-api/build/libs/
total 8
-rw-r--r--  1 prayagupd  184630988  1644 Jul 21 16:00 microservice-api-1.0.jar

λ ls -l microservice-schema/build/libs/
total 8
-rw-r--r--  1 prayagupd  184630988  1233 Jul 21 16:00 microservice-schema-1.0.jar
```

run microservice
--------

```
λ gradle clean run

> Task :microservice-api:run
===================
1-hello microservice
===================

BUILD SUCCESSFUL in 0s
6 actionable tasks: 6 executed
```

```bash
λ java -jar microservice-api/build/libs/microservice-api-assembly-1.0.jar 
===================
1-hello microservice
===================


curl localhost:8080/ads
[{"ads":["1","2"]}]

```
