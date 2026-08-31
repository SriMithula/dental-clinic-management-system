package com.sunrisedental.api;

import java.io.IOException;
import java.util.List;

import com.sunrisedental.dao.TreatmentDao;
import com.sunrisedental.dao.impl.TreatmentDaoImpl;
import com.sunrisedental.dto.TreatmentDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/treatments")
public class TreatmentApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TreatmentDao treatmentDao = new TreatmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TreatmentDto> treatments = treatmentDao.getActiveTreatments();

        ApiUtil.writeJson(response, HttpServletResponse.SC_OK, treatments);
    }
}