package com.sunrisedental.apiclient;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ApiClientDemo {

    public static void main(String[] args) throws Exception {

        String baseUrl = args.length > 0 ? args[0] : "http://localhost:8080/dental-clinic-management-system";

        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        GsonBuilder prettyGson = new GsonBuilder().setPrettyPrinting();

        System.out.println("=== Sunrise Dental Clinic - REST API client demo ===");
        System.out.println("Talking to: " + baseUrl);
        System.out.println("(This is a separate JVM/process from the web app -- it only");
        System.out.println(" ever communicates with it over HTTP, exactly like a browser would.)");
        System.out.println();

        System.out.println("--- GET /api/dentists ---");
        System.out.println(get(client, baseUrl + "/api/dentists"));
        System.out.println();

        System.out.println("--- GET /api/treatments ---");
        System.out.println(get(client, baseUrl + "/api/treatments"));
        System.out.println();

        System.out.println("--- GET /api/patients?search= ---");
        System.out.println(get(client, baseUrl + "/api/patients?search="));
        System.out.println();

        if (args.length >= 3) {

            String username = args[1];
            String password = args[2];

            System.out.println("--- POST /login (as " + username + ") ---");

            boolean loggedIn = login(client, baseUrl, username, password);

            if (!loggedIn) {
                System.out.println("Login failed -- skipping the write demo.");
                return;
            }

            System.out.println("Logged in successfully.");
            System.out.println();

            JsonObject newAppointment = new JsonObject();
            newAppointment.addProperty("patientName", "API Demo Patient");
            newAppointment.addProperty("contactNo", "0770000000");
            newAppointment.addProperty("address", "Demo Address, Colombo");
            newAppointment.addProperty("appointmentNo", "API-DEMO-" + System.currentTimeMillis());
            newAppointment.addProperty("appointmentDate", LocalDate.now().plusDays(1).toString());
            newAppointment.addProperty("appointmentTime", "10:00");
            newAppointment.addProperty("dentistId", 1);
            newAppointment.addProperty("treatmentId", 1);

            System.out.println("--- POST /api/appointments ---");
            System.out.println("Request body: " + prettyGson.create().toJson(newAppointment));
            System.out.println(post(client, baseUrl + "/api/appointments", newAppointment.toString()));

        } else {
            System.out.println("(Re-run with: <baseUrl> <username> <password>");
            System.out.println(" to also demonstrate logging in and creating an");
            System.out.println(" appointment via POST -- i.e. a full write call.)");
        }
    }

    private static String get(HttpClient client, String url) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return "HTTP " + response.statusCode() + " -> " + response.body();
    }

    private static String post(HttpClient client, String url, String jsonBody) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return "HTTP " + response.statusCode() + " -> " + response.body();
    }

    private static boolean login(HttpClient client, String baseUrl, String username, String password)
            throws IOException, InterruptedException {

        String form = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)
                + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/login"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String location = response.headers().firstValue("Location").orElse("");

        return response.statusCode() / 100 == 3 && !location.contains("error=true");
    }
}