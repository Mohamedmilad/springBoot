package com.example.demo.service;

import com.example.demo.entity.Companies;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {
    Companies save(Companies companies);
    List<Companies> getAll();
    void deleteRow(@PathVariable Long id);
    boolean idExisted(@PathVariable Long id);
    Optional<Companies> findById(@PathVariable Long id);
}
