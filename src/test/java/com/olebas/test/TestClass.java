package com.olebas.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.olebas.util.TokenHelper;
import com.olebas.util.ReadConfig;
import com.olebas.util.RequestHelper;
import com.olebas.util.ResponseHelper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class TestClass extends TokenHelper {

    RequestHelper requestHelper = new RequestHelper();
    ResponseHelper responseHelper = new ResponseHelper();
    ReadConfig config = new ReadConfig();

    @Test
    public void testGraphqlWithInputStream() throws IOException {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("clientId", "284492");

        String request = requestHelper.createRequest("app-list.graphql", variables);

        StringBuilder url = new StringBuilder().append(config.getGraphqlURL()).append("?").append("token").append("=").append(createToken());
        System.out.println(url);

        Response response = responseHelper.prepareResponse(request, url.toString());

        Assert.assertEquals(response.code(), 200, "Response code assertion");

        JsonNode responseJson = responseHelper.getJsonResponse(response);

        String text = responseJson.get("data").get("applicationList").get("items").findValues("m_cl_full_name").toString();

        System.out.println(text.toString());


    }
}
