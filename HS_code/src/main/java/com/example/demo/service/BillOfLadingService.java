package com.example.demo.service;

import com.example.demo.entity.BillOfLading;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface BillOfLadingService {
    BillOfLading save(BillOfLading companies);
    List<BillOfLading> getAll();
    void deleteRow(@PathVariable Long id);
    boolean idExisted(@PathVariable Long id);
    Optional<BillOfLading> findById(@PathVariable Long id);
}
