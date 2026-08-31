package com.sunrisedental.api;

import java.io.IOException;
import java.sql.Connection;

import com.google.gson.JsonObject;
import com.sunrisedental.dao.AppointmentDao;
import com.sunrisedental.dao.impl.AppointmentDaoImpl;
import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.AppointmentDto;
import com.sunrisedental.dto.CommonResponse;
import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/appointments")
public class AppointmentApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final AppointmentService appointmentService = new AppointmentService();
    private final AppointmentDao appointmentDao = new AppointmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            CommonResponse cr = appointmentService.getAppointments();

            if (!cr.status) {
                ApiUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cr.error);
                return;
            }

            ApiUtil.writeJson(response, HttpServletResponse.SC_OK, cr.extra);
            return;
        }

        int appointmentId;

        try {
            appointmentId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            ApiUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "id must be a number.");
            return;
        }

        Connection connection = null;

        try {
            connection = DBConnection.getConnection();

            AppointmentDto appointment = appointmentDao.findById(appointmentId, connection);

            if (appointment == null) {
                ApiUtil.writeError(response, HttpServletResponse.SC_NOT_FOUND, "Appointment not found.");
                return;
            }

            ApiUtil.writeJson(response, HttpServletResponse.SC_OK, appointment);

        } catch (Exception e) {
            e.printStackTrace();
            ApiUtil.writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load appointment.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = ApiUtil.getSessionUserId(request);

        if (userId == -1) {
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

        AppointmentDealer dealer;

        try {
            dealer = new AppointmentDealer.Builder().fillViaJson(body).build();
        } catch (IllegalArgumentException e) {
            ApiUtil.writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid appointmentDate or appointmentTime format.");
            return;
        }

        CommonResponse cr = appointmentService.saveAppointment(dealer, userId);

        if (!cr.status) {
            boolean conflict = cr.error != null && cr.error.toLowerCase().contains("already exists")
                    || cr.error != null && cr.error.toLowerCase().contains("already has an appointment");

            int statusCode = conflict ? HttpServletResponse.SC_CONFLICT : HttpServletResponse.SC_BAD_REQUEST;

            ApiUtil.writeError(response, statusCode, cr.error);
            return;
        }

        ApiUtil.writeJson(response, HttpServletResponse.SC_CREATED, cr);
    }
}