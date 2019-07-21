package org.duwamish.microservice.schema;

public class MicroserviceResponse {
    public String requestId;
    public String messsage;

    public MicroserviceResponse(String requestId, String messsage) {
        this.requestId = requestId;
        this.messsage = messsage;
    }
}
