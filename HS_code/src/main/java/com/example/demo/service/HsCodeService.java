package com.example.demo.service;

import com.example.demo.entity.HsCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface HsCodeService {
    ResponseEntity<?> addHsCode(HsCode hsCode);
    ResponseEntity<?> getAll();
    ResponseEntity<String> dropTable();
    ResponseEntity<String> createTable();
     ResponseEntity<String> deleteRow(@PathVariable Long id);
     ResponseEntity<String> updateTable(@PathVariable Long id, @RequestBody HsCode hsCode);
}
