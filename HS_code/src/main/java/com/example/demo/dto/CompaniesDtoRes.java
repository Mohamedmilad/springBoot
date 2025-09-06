package com.example.demo.dto;

import com.example.demo.entity.BillOfLading;

import java.time.LocalDateTime;
import java.util.List;

public class CompaniesDtoRes {
    private String taxNumber;
    private String name;
    private Long id;
    private LocalDateTime timeStamp;
//    private List<BillOfLading> billsOfLading;

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

//    public List<BillOfLading> getBillsOfLading() {
//        return billsOfLading;
//    }
//
//    public void setBillsOfLading(List<BillOfLading> billsOfLading) {
//        this.billsOfLading = billsOfLading;
//    }
}
