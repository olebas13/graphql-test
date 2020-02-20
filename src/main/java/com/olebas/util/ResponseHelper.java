package com.olebas.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class ResponseHelper {

    private OkHttpClient client = new OkHttpClient();

    public Response prepareResponse(String graphqlPayload, String url) throws IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"),
                graphqlPayload);
        Request request = new Request.Builder().url(url).post(body).build();
        return client.newCall(request).execute();
    }

    public JsonNode getJsonResponse(Response response) throws IOException {
        String jsonData = response.body().string();
        return new ObjectMapper().readTree(jsonData);
    }

}
