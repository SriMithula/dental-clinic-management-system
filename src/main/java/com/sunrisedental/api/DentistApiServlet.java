package com.sunrisedental.api;

import java.io.IOException;
import java.util.List;

import com.sunrisedental.dao.DentistDao;
import com.sunrisedental.dao.impl.DentistDaoImpl;
import com.sunrisedental.dto.DentistDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/dentists")
public class DentistApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final DentistDao dentistDao = new DentistDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<DentistDto> dentists = dentistDao.getActiveDentists();

        ApiUtil.writeJson(response, HttpServletResponse.SC_OK, dentists);
    }
}
