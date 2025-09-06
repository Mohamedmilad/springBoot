package com.example.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "companies")
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TAX_NUMBER",length = 64, nullable = false, unique = true)
    private String taxNumber;

    @Column(name="NAME",length = 3000, nullable = false)
    private String name;

    @Column(name = "TIME_STAMP", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;

    @Column(name="CHANGER_ID",nullable = false)
    private Long changer_id;

    @OneToMany(mappedBy = "agent")
    private List<BillOfLading> billsOfLading;

    public Companies() {}

    public Companies(String taxNumber, String name) {
        this.taxNumber = taxNumber;
        this.name = name;
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

    public String getTaxNumber() { return taxNumber; }
    public void setTaxNumber(String taxNumber) { this.taxNumber = taxNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getChanger_id() {return changer_id;}
    public void setChanger_id(Long changer_id) {this.changer_id = changer_id;}

    public LocalDateTime getTimeStamp() {return timeStamp;}
    public void setTimeStamp(LocalDateTime timeStamp) {this.timeStamp = timeStamp;}

    public List<BillOfLading> getBillsOfLading() {return billsOfLading;}
    public void setBillsOfLading(List<BillOfLading> billsOfLading) {this.billsOfLading = billsOfLading;}

}
