package org.lamatola.microservice.schema;

public class MicroserviceResponse<A> {
    private A payload;

    public MicroserviceResponse(){

    }

    public MicroserviceResponse(A a) {
        this.payload = a;
    }

    public A getPayload() {
        return payload;
    }

    public void setPayload(A payload) {
        this.payload = payload;
    }
}
