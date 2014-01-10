package com.bardlind.jsonloader;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: baardl
 * Date: 1/9/14
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestCypherMapperTest {
    @Test
    public void testFindId() throws Exception {
        Long id = RestCypherMapper.findIds(jsonId).get(0);
        assertNotNull(id);
        assertEquals(new Long(1),id);
    }
    @Test
    public void testFindIds() throws Exception {
       ArrayList<Long> ids = RestCypherMapper.findIds(jsonIds);
        assertEquals(new Long(0), ids.get(0));
        assertEquals(new Long(1), ids.get(1));
        assertEquals(new Long("12300100202"), ids.get(2));
    }

    private static String jsonId = "{\n" +
            "  \"columns\" : [ \"id(store)\" ],\n" +
            "  \"data\" : [ [ 1 ] ]\n" +
            "}";
    private static String jsonIds = "{\n" +
            "  \"columns\" : [ \"id(store)\" ],\n" +
            "  \"data\" : [ [0],[ 1 ], [12300100202] ]\n" +
            "}";
}
