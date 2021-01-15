package com.evolyb.pdf.controller;

import com.evolyb.pdf.model.ReportModel;
import com.evolyb.pdf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;

@Controller
public class PdfController {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    ReportService reportService;

    @GetMapping("/view")
    public String getReport(Model model){
        ReportModel reportModel = reportService.demoGetModel();
        model.addAttribute("model",reportModel);
        return "report";
    }

    @GetMapping("/pdf")
    @ResponseBody
    public ResponseEntity<Object> exportReport(){

       ByteArrayOutputStream stream = reportService.getReportForExport();
        byte[] bytes = stream.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }


}
