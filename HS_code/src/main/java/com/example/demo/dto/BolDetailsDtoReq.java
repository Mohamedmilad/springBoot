package com.example.demo.dto;

import java.math.BigDecimal;

public class BolDetailsDtoReq {
    private String serial;
    private Long count;
    private BigDecimal weight;
    private BigDecimal volume;
    private Long hsCodeId;
    private Long billOfLadingId;

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

    public Long getHsCodeId() {
        return hsCodeId;
    }

    public void setHsCodeId(Long hsCodeId) {
        this.hsCodeId = hsCodeId;
    }

    public Long getBillOfLadingId() {
        return billOfLadingId;
    }

    public void setBillOfLadingId(Long billOfLadingId) {
        this.billOfLadingId = billOfLadingId;
    }
}
