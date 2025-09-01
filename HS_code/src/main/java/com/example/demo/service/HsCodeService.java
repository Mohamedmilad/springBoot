package com.example.demo.service;

import com.example.demo.entity.HsCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface HsCodeService {
    HsCode save(HsCode hsCode);
    List<HsCode> getAll();
     void deleteRow(@PathVariable Long id);
     boolean idExisted(@PathVariable Long id);
    Optional<HsCode> findById(@PathVariable Long id);
}
