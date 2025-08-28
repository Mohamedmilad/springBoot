package com.example.demo.service;

import com.example.demo.entity.HsCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface HsCodeService {
    ResponseEntity<HsCode> addHsCode(HsCode hsCode);
     List<HsCode> getAll();
     String dropTable();
     String createTable();
     String deleteRow(@PathVariable Long id);
     String updateTable(@PathVariable Long id, @RequestBody HsCode hsCode);
}
