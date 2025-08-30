package com.example.demo.controller;
import com.example.demo.service.HsCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.HsCode;
import java.util.List;

@RestController
@RequestMapping("/hs")

public class HsCodeController {
    private final HsCodeService hsCodeService;

    public HsCodeController(HsCodeService hsCodeService) {
        this.hsCodeService = hsCodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHsCode(@RequestBody HsCode hsCode) {
        return hsCodeService.addHsCode(hsCode);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return hsCodeService.getAll();
    }

    @DeleteMapping("/drop")
    public ResponseEntity<String> dropTable() {
        return hsCodeService.dropTable();
    }
    @PostMapping("/create")
    public ResponseEntity<String> createTable() {
        return hsCodeService.createTable();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        return hsCodeService.deleteRow(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTable(@PathVariable Long id, @RequestBody HsCode hsCode) {
        return hsCodeService.updateTable(id, hsCode);
    }
}
