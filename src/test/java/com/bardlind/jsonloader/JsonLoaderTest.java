package com.bardlind.jsonloader;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JsonLoaderTest {
    private EmbeddedNeo4JServer neo4JServer;
    private JsonLoader jsonLoader;

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception {
        if (jsonLoader != null) {
            jsonLoader.postOperation(deleteAllDevelopers);
        }
    }


    @Test
    public void postData() {
        jsonLoader = new JsonLoader();
        jsonLoader.postOperation(createDevelopers);
        String existingDevelopers = jsonLoader.postOperation(queryForDevelopers);
        System.out.println("Existing: " + existingDevelopers);
        assertTrue(existingDevelopers.contains("Andres"));
        assertTrue(existingDevelopers.contains("Michael"));

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
            "  \"query\" : \"MATCH (n:Person) WHERE n.name in ['Andres', 'Michael'] RETURN collect(n.name) \",\n" +
            "  \"params\" : {\n" +
            "  }\n" +
            "}";
    private static String deleteAllDevelopers = "{\n" +
            "  \"query\" : \"MATCH (n:Person) WHERE n.name in ['Andres', 'Michael'] DELETE n \",\n" +
            "  \"params\" : {\n" +
            "  }\n" +
            "}";
}
