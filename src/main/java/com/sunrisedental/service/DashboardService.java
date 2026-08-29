package com.sunrisedental.service;

import com.sunrisedental.dao.DashboardDao;
import com.sunrisedental.dao.impl.DashboardDaoImpl;
import com.sunrisedental.dto.DashboardDto;

public class DashboardService {

    private DashboardDao dashboardDao;

    public DashboardService() {
        this.dashboardDao = new DashboardDaoImpl();
    }

    public DashboardDto getDashboardData() {
        return dashboardDao.getDashboardData();
    }
}