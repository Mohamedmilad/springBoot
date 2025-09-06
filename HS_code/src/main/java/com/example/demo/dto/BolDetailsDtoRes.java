package com.example.demo.dto;

import com.example.demo.entity.BillOfLading;
import com.example.demo.entity.HsCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BolDetailsDtoRes {
    private Long id;
    private LocalDateTime timeStamp;
    private String serial;
    private Long count;
    private BigDecimal weight;
    private BigDecimal volume;
    private HsCode hsCode;
    private BillOfLading billOfLading;

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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public HsCode getHsCode() {
        return hsCode;
    }

    public void setHsCode(HsCode hsCode) {
        this.hsCode = hsCode;
    }

    public BillOfLading getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(BillOfLading billOfLading) {
        this.billOfLading = billOfLading;
    }
}
