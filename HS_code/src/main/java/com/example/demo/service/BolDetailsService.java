package com.example.demo.service;

import com.example.demo.entity.BolDetails;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface BolDetailsService {
    BolDetails save(BolDetails bolDetails);
    List<BolDetails> getAll();
    void deleteRow(@PathVariable Long id);
    boolean idExisted(@PathVariable Long id);
    Optional<BolDetails> findById(@PathVariable Long id);
}
