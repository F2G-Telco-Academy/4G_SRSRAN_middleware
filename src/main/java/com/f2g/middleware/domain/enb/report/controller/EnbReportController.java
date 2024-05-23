package com.f2g.middleware.domain.enb.report.controller;

import com.f2g.middleware.domain.enb.report.model.Report;
import com.f2g.middleware.domain.enb.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/middleware-service/api/v1/enb/report")
public class EnbReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public List<Report> getReports() {
        return reportService.getReport();
    }

    @GetMapping("/last-report")
    public Report getLastReport() {
        return reportService.getLastReport();
    }
}
