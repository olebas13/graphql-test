package com.olebas.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vimalselvam.graphql.GraphqlTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RequestHelper {

    public String createRequest(String fileName, HashMap<String, String> variables) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/graphql/" + fileName);
        ObjectNode varBuilder = new ObjectMapper().createObjectNode();
        for (HashMap.Entry<String, String> variable : variables.entrySet()) {
            varBuilder.put(variable.getKey(), variable.getValue());
        }
        String graphqlPayload = GraphqlTemplate.parseGraphql(file, varBuilder);
        return graphqlPayload;
    }
}
