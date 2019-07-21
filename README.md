microservice skeleton
-----------------------

This demonstrates how to create a microservice with api and schema as separate jars.

- microservice-api
- microservice-schema
 

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

artifacts
---------


```bash
λ ll microservice-api/build/distributions/
total 16864
8641996081 -rw-r--r--  1 prayagupd  184630988   4.4M Jul 21 15:36 microservice-api-1.0.tar
8641996082 -rw-r--r--  1 prayagupd  184630988   3.9M Jul 21 15:36 microservice-api-1.0.zip
```