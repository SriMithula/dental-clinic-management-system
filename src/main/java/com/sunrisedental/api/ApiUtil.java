package com.sunrisedental.api;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class ApiUtil {

    public static final Gson GSON = new Gson();

    private ApiUtil() {
    }

    public static JsonObject readJsonBody(HttpServletRequest request) throws IOException {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        if (sb.length() == 0) {
            return new JsonObject();
        }

        try {
            return GSON.fromJson(sb.toString(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("Malformed JSON body", e);
        }
    }

    public static void writeJson(HttpServletResponse response, int statusCode, Object body) throws IOException {

        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(GSON.toJson(body));
    }

    public static void writeError(HttpServletResponse response, int statusCode, String message) throws IOException {

        JsonObject error = new JsonObject();
        error.addProperty("error", message);

        writeJson(response, statusCode, error);
    }

    public static int getSessionUserId(HttpServletRequest request) {

        Object userId = request.getSession(false) != null
                ? request.getSession(false).getAttribute("userId")
                : null;

        return userId instanceof Integer ? (Integer) userId : -1;
    }
}