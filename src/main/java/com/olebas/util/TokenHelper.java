package com.olebas.util;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class TokenHelper {

    ResponseHelper responseHelper = new ResponseHelper();
    ReadConfig config = new ReadConfig();
    RequestHelper requestHelper = new RequestHelper();

    public String createToken() throws IOException {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("login", config.getAdminLogin());
        variables.put("password", config.getAdminPassword());
        String url = config.getGraphqlURL();

        String request = requestHelper.createRequest("login-admin.graphql", variables);
        Response response = responseHelper.prepareResponse(request, url);

        Assert.assertEquals(response.code(), 200, "Response code assertion");

        JsonNode responseJson = responseHelper.getJsonResponse(response);

        String token = responseJson.get("data").get("login").get("token").asText();
        return token;
    }

}
