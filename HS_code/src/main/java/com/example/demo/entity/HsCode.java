package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hs_code")
public class HsCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false, unique = true)
    private String code;

    @Column(length = 3000, nullable = false)
    private String name;

    @Column(name = "timestamp", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;

    public HsCode() {}

    public HsCode(String code, String name) {
        this.code = code;
        this.name = name;
        this.timeStamp = LocalDateTime.now();
    }
    @PrePersist
    protected void onCreate() {
        this.timeStamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getTimestamp() { return timeStamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timeStamp = timestamp; }


}
