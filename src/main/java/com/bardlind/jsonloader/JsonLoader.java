package com.bardlind.jsonloader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JsonLoader {
    private static final String SERVER_ROOT_URI = "http://localhost:7474/";
    private static final String RESPONSE_TYPES = "application/json; charset=UTF-8";
    private WebTarget target;
    public static void main(String[] args) {
        JsonLoader loader = new JsonLoader();
        String responseMsg = getOperation(loader);
        System.out.println("Get: " + responseMsg);

        responseMsg = postOperation(loader);
        System.out.println("Post: " + responseMsg);

    }

    private static String getOperation(JsonLoader loader) {
        Client c = ClientBuilder.newClient();
        loader.target = c.target(SERVER_ROOT_URI);
        return loader.target.path("db/data/node/101/relationships/in").request(RESPONSE_TYPES).get(String.class);
    }

    private static String postOperation(JsonLoader loader) {
        Client c = ClientBuilder.newClient();
        loader.target = c.target(SERVER_ROOT_URI).path("db/data/cypher");
        String data = "{\n" +
                "  \"query\" : \"CREATE (n:Person { props } ) RETURN n\",\n" +
                "  \"params\" : {\n" +
                "    \"props\" : [ {\n" +
                "      \"name\" : \"Andres\",\n" +
                "      \"position\" : \"Developer\"\n" +
                "    }, {\n" +
                "      \"name\" : \"Michael\",\n" +
                "      \"position\" : \"Developer\"\n" +
                "    } ]\n" +
                "  }\n" +
                "}";
        return loader.target.request(RESPONSE_TYPES).post(Entity.entity(data, MediaType.APPLICATION_JSON), String.class);
    }
}
