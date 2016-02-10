package com.mcl.resource;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * Created by Kim on 2016-02-10.
 */
public class ConnResource extends CoapResource {
    public ConnResource() {
        super("helloWorld");
        getAttributes().setTitle("Hello-World Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("Hello World!");
    }
}
