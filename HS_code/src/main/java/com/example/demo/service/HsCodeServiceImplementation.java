package com.example.demo.service;

import com.example.demo.entity.HsCode;
import com.example.demo.repository.HsCodeRepository;
import com.example.demo.specification.HsCodeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Service
public class HsCodeServiceImplementation implements HsCodeService {
    private final HsCodeRepository hsCodeRepository;

    public HsCodeServiceImplementation(HsCodeRepository hsCodeRepository, JdbcTemplate jdbcTemplate) {
        this.hsCodeRepository = hsCodeRepository;
    }

    public HsCode  save(HsCode hsCode) {
        return hsCodeRepository.save(hsCode);
    }

    public List<HsCode> getAll(){
        return hsCodeRepository.findAll();
    }
    public void deleteRow(@PathVariable Long id){
        hsCodeRepository.deleteById(id);
    }
    public boolean idExisted(@PathVariable Long id){
        return hsCodeRepository.existsById(id);
    }
    public Optional<HsCode> findById(@PathVariable Long id){
        return hsCodeRepository.findById(id);
    }
    public Optional<HsCode> findByCode(String code) {
        Specification<HsCode> spec = HsCodeSpecification.hasCode(code);
        return hsCodeRepository.findOne(spec);
    }
}
