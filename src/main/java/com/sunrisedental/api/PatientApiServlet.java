package com.sunrisedental.api;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonObject;
import com.sunrisedental.dao.PatientDao;
import com.sunrisedental.dao.impl.PatientDaoImpl;
import com.sunrisedental.dto.PatientDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/patients")
public class PatientApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final PatientDao patientDao = new PatientDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        if (search == null) {
            search = "";
        }

        List<PatientDto> patients = patientDao.searchPatient(search.trim());

        ApiUtil.writeJson(response, HttpServletResponse.SC_OK, patients);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (ApiUtil.getSessionUserId(request) == -1) {
            ApiUtil.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in.");
            return;
        }

        JsonObject body;

        try {
            body = ApiUtil.readJsonBody(request);
        } catch (IOException e) {
            ApiUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Malformed JSON body.");
            return;
        }

        String name = body.has("name") && !body.get("name").isJsonNull() ? body.get("name").getAsString().trim() : "";
        String contactNo = body.has("contactNo") && !body.get("contactNo").isJsonNull() ? body.get("contactNo").getAsString().trim() : "";
        String address = body.has("address") && !body.get("address").isJsonNull() ? body.get("address").getAsString().trim() : "";

        if (name.isEmpty() || contactNo.isEmpty() || address.isEmpty()) {
            ApiUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "name, contactNo and address are all required.");
            return;
        }

        if (patientDao.isPatientNameExists(name)) {
            ApiUtil.writeError(response, HttpServletResponse.SC_CONFLICT,
                    "A patient with this name already exists.");
            return;
        }

        int newPatientId = patientDao.createPatient(name, contactNo, address);

        if (newPatientId <= 0) {
            ApiUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create patient.");
            return;
        }

        PatientDto created = new PatientDto();
        created.id = newPatientId;
        created.name = name;
        created.tel = contactNo;
        created.address = address;

        ApiUtil.writeJson(response, HttpServletResponse.SC_CREATED, created);
    }
}