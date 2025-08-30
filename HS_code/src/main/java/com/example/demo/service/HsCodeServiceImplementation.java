package com.example.demo.service;

import com.example.demo.entity.HsCode;
import com.example.demo.repository.HsCodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;
@Service
public class HsCodeServiceImplementation implements HsCodeService {
    private final HsCodeRepository hsCodeRepository;
    private JdbcTemplate jdbcTemplate;

    public HsCodeServiceImplementation(HsCodeRepository hsCodeRepository, JdbcTemplate jdbcTemplate) {
        this.hsCodeRepository = hsCodeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseEntity<?>  addHsCode(HsCode hsCode) {
        if (hsCode.getCode() == null || hsCode.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code and name cannot be NULL");
        }
        return ResponseEntity.ok(hsCodeRepository.save(hsCode));
    }

    public ResponseEntity<?> getAll(){
        try {
            List<HsCode> hsCodes = hsCodeRepository.findAll();
            if (hsCodes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rows found in hsCode"); //404 not 204 to add a content
            }
            return ResponseEntity.ok(hsCodes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching the table"+e.getMessage());
        }
    }

    public ResponseEntity<String> dropTable(){
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS hs_code");
            return ResponseEntity.ok("Table 'hs_code' dropped successfully!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to drop table: " + e.getMessage());
        }
    }

    public ResponseEntity<String> createTable(){
        String sql = """
            CREATE TABLE IF NOT EXISTS hs_code (
            id INT AUTO_INCREMENT PRIMARY KEY,
            code BIGINT NOT NULL,           
            name VARCHAR(3000) NOT NULL,         
            timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        )
            
        """;
        try {
            jdbcTemplate.execute(sql);
            return ResponseEntity.ok(" Table 'hs_code' has been created.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Create table" + e.getMessage());

        }
    }

    public ResponseEntity<String> deleteRow(@PathVariable Long id){
        if (!hsCodeRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Row with ID=" + id + " doesn't exist in 'hs_code'");
        }
        hsCodeRepository.deleteById(id);
        return ResponseEntity.ok("Row with ID=\" + id + \" in 'hs_code' deleted successfully!");
    }

    public ResponseEntity<String> updateTable(@PathVariable Long id, @RequestBody HsCode hsCode) {
        if (!hsCodeRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Row with ID=" + id + " doesn't exist in 'hs_code'");
        }
        HsCode hs = hsCodeRepository.findById(id).orElseThrow();
        if (hsCode.getCode() == null && hsCode.getName() == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        if (hsCode.getCode() == null ) {
            hs.setName(hsCode.getName());
            hs.setCode(hs.getCode());
        } else if (hsCode.getName() == null) {
            hs.setName(hs.getName());
            hs.setCode(hsCode.getCode());
        }
        else {
            hs.setName(hsCode.getName());
            hs.setCode(hsCode.getCode());
        }
        hsCodeRepository.save(hs);
        return ResponseEntity.ok("ID " + id + " in 'hs_code' updated successfully!");
    }

}
