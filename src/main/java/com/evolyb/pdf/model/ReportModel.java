package com.evolyb.pdf.model;

import lombok.Data;

import java.util.List;

@Data
public class ReportModel {
    private String staff;
    private String date;
    private List<ReportData> data;
}
