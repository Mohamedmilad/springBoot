package com.example.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "bill_of_lading")
public class BillOfLading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false, unique = true)
    private String nbr;

    @Column(name = "time_stamp", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private Long changer_id;

    public BillOfLading() {}

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Companies agent;

    @OneToMany(mappedBy = "billOfLading")
    private List<BolDetails> bolDetails;

    public BillOfLading(String nbr, Companies agent) {
        this.nbr = nbr;
        this.agent = agent;
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

    public String getNbr() { return nbr; }
    public void setNbr(String nbr) { this.nbr = nbr; }

    public Companies getAgent() { return agent; }
    public void setAgent(Companies agent) { this.agent = agent; }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getChanger_id() {
        return changer_id;
    }

    public void setChanger_id(Long changer_id) {
        this.changer_id = changer_id;
    }

    public List<BolDetails> getBolDetails() {
        return bolDetails;
    }

    public void setBolDetails(List<BolDetails> bolDetails) {
        this.bolDetails = bolDetails;
    }
}
