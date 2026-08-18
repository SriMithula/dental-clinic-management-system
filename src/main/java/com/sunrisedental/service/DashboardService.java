package com.sunrisedental.service;

import com.sunrisedental.dao.DashboardDao;
import com.sunrisedental.dto.DashboardDto;

public class DashboardService {

    private DashboardDao dashboardDao;

    public DashboardService() {
        this.dashboardDao = new DashboardDao();
    }

    public DashboardDto getDashboardData() {
        return dashboardDao.getDashboardData();
    }
}