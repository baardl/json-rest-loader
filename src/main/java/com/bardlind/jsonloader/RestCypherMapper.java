package com.bardlind.jsonloader;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class RestCypherMapper {

    /**
     * Parse the id's from a result.
     * This method require a input on this format:
     * {
     "columns" : [ "id(store)" ],
     "data" : [ [ 1 ] ]
     }
     or
     {
     "columns" : [ "id(store)" ],
     "data" : [ [ 1 ],[2],[12300100202] ]
     }
     * @param json
     * @return
     * @throws java.io.IOException
     */
    public static ArrayList<Long> findIds(String json) throws IOException {
        System.out.println("json:" + json);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Long> nodeIs = new ArrayList<Long>();
        Map<String,ArrayList> result = mapper.readValue(json, Map.class);
        if (result != null && result.get("data") != null) {
            ArrayList<ArrayList> arrayIds = result.get("data");
            for (ArrayList arrayId : arrayIds) {
                Object numbrer = arrayId.get(0);
                if (numbrer instanceof Integer) {
                    nodeIs.add(new Long((Integer)arrayId.get(0)));
                } else {
                    nodeIs.add((Long)arrayId.get(0));
                }

            }
        }
        return nodeIs;
    }
}
