package com.example.demo.dto;

import com.example.demo.entity.Companies;

import java.time.LocalDateTime;

public class BillOfLadingDtoRes {
    private Long id;
    private String nbr;
    private Companies agent;
    private LocalDateTime timeStamp;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNbr() {
        return nbr;
    }
    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public Companies getAgent() {
        return agent;
    }

    public void setAgent(Companies agent) {
        this.agent = agent;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
