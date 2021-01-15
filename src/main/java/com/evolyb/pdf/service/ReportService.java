package com.evolyb.pdf.service;

import com.evolyb.pdf.model.ReportData;
import com.evolyb.pdf.model.ReportModel;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    SpringTemplateEngine templateEngine;

    public ReportModel demoGetModel(){
        ReportModel reportModel = new ReportModel();
        reportModel.setStaff("Đoàn Hùng Dũng");
        reportModel.setDate("24/12/2020");
        List<ReportData> data = new ArrayList<>();
        data.add(new ReportData("1","Cafe"));
        data.add(new ReportData("2","Coke"));
        reportModel.setData(data);
        return reportModel;
    }

    public ByteArrayOutputStream getReportForExport(){
        ReportModel reportModel = demoGetModel();
        Context context = new Context();
        context.setVariable("model",reportModel);
        String reportHtml = templateEngine.process("report",context);



        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(reportHtml, stream, converterProperties);
        return stream;
    }
}
