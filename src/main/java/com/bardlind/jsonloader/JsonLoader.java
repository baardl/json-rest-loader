package com.bardlind.jsonloader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class JsonLoader {
    public static final String SERVER_ROOT_URI = "http://localhost:7474/";
    public static final String RESPONSE_TYPES = "application/json; charset=UTF-8";
    private WebTarget target;
    public static void main(String[] args) {
        JsonLoader loader = new JsonLoader();
        String responseMsg = loader.getOperation();
        System.out.println("Get: " + responseMsg);

        responseMsg = loader.postOperation(createDevelopers);
        System.out.println("Post: " + responseMsg);

    }

    public String getOperation() {
        Client c = ClientBuilder.newClient();
        target = c.target(SERVER_ROOT_URI);
        return target.path("db/data/node/101/relationships/in").request(RESPONSE_TYPES).get(String.class);
    }


    public String postOperation(String cypherInJson) {
        Client c = ClientBuilder.newClient();
        target = c.target(SERVER_ROOT_URI).path("db/data/cypher");

        return target.request(RESPONSE_TYPES).post(Entity.entity(cypherInJson, MediaType.APPLICATION_JSON), String.class);
    }
    private static String createDevelopers = "{\n" +
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

    private static String queryForDevelopers = "{\n" +
            "  \"query\" : \"MATCH (n) WHERE n.name in ['Andres', 'Michael'] RETURN collect(n.name)\",\n" +
            "  \"params\" : {\n" +
            "  }\n" +
            "}";
}
