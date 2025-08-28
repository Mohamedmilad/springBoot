package com.example.demo.service;

import com.example.demo.entity.HsCode;
import com.example.demo.repository.HsCodeRepository;
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
    public ResponseEntity<HsCode>  addHsCode(HsCode hsCode) {
        return ResponseEntity.ok(hsCodeRepository.save(hsCode));
    }
    public List<HsCode> getAll(){
        return hsCodeRepository.findAll();
    }
    public String dropTable(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS hs_code");
        return "Table 'hs_code' dropped successfully!";
    }
    public String createTable(){
        String sql = """
            CREATE TABLE IF NOT EXISTS hs_code (
            id INT AUTO_INCREMENT PRIMARY KEY,
            code BIGINT NOT NULL,           
            name VARCHAR(3000) NOT NULL,         
            timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        )
            
        """;
        jdbcTemplate.execute(sql);
        return " Table 'hs_code' has been created.";
    }
    public String deleteRow(@PathVariable Long id){

        if (!hsCodeRepository.existsById(id)) {
            return "Row with ID=" + id + " doesn't exist in 'hs_code'";
        }
        hsCodeRepository.deleteById(id);
        return "Row with ID=" + id + " in 'hs_code' deleted successfully!";
    }
    public String updateTable(@PathVariable Long id, @RequestBody HsCode hsCode) {
        if (!hsCodeRepository.existsById(id)) {
            return "Row with ID=" + id + " doesn't exist in 'hs_code'";
        }
        HsCode hs = hsCodeRepository.findById(id).orElseThrow();
        hs.setName(hsCode.getName());
        hs.setCode(hsCode.getCode());
        hsCodeRepository.save(hs);
        return "ID " + id + " in 'hs_code' updated successfully!";
    }
}
