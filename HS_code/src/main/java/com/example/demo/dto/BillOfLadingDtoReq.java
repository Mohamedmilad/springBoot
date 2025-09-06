package com.example.demo.dto;

import com.example.demo.entity.Companies;

public class BillOfLadingDtoReq {
    private String nbr;
    private Long agentId;

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
}
