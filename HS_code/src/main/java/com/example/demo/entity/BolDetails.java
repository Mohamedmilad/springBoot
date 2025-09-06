package com.example.demo.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "bol_details_tr")
public class BolDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false, unique = true)
    private String serial;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal weight;

    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal volume;

    @Column(name = "time_stamp", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private Long changer_id;

//    the owner of the relationship
    @ManyToOne
    @JoinColumn(name = "hs_code_id")
    private HsCode hsCode;

    // Many details belong to one Bill of Lading
    @ManyToOne
    @JoinColumn(name = "bol_id")
    private BillOfLading billOfLading;

    public BolDetails() {}

    public BolDetails(String serial, Long count, BigDecimal weight, BigDecimal volume, HsCode hsCode, BillOfLading billOfLading) {
        this.serial = serial;
        this.count = count;
        this.weight = weight;
        this.volume = volume;
        this.hsCode = hsCode;
        this.billOfLading = billOfLading;
        this.timeStamp = LocalDateTime.now();
        Random random = new Random();
        this.changer_id = (long) random.nextInt(Integer.MAX_VALUE);
    }
    @PrePersist
    protected void onCreate() {
        this.timeStamp = LocalDateTime.now();
        Random random = new Random();
        this.changer_id = (long) random.nextInt(Integer.MAX_VALUE);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimeStamp() { return timeStamp; }
    public void setTimeStamp(LocalDateTime timestamp) { this.timeStamp = timestamp; }

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

    public Long getChanger_id() {
        return changer_id;
    }

    public void setChanger_id(Long changer_id) {
        this.changer_id = changer_id;
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
