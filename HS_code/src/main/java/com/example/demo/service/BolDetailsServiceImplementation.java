package com.example.demo.service;

import com.example.demo.entity.BolDetails;
import com.example.demo.entity.HsCode;
import com.example.demo.repository.BolDetailsRepository;
import com.example.demo.specification.BolDetailsSpecification;
import com.example.demo.specification.HsCodeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BolDetailsServiceImplementation implements BolDetailsService{
    private final BolDetailsRepository bolDetailsRepository;

    public BolDetailsServiceImplementation(BolDetailsRepository bolDetailsRepository) {
        this.bolDetailsRepository = bolDetailsRepository;
    }

    public BolDetails save(BolDetails bolDetails) {
        return bolDetailsRepository.save(bolDetails);
    }

    public List<BolDetails> getAll(){
        return bolDetailsRepository.findAll();
    }
    public void deleteRow(@PathVariable Long id){
        bolDetailsRepository.deleteById(id);
    }
    public boolean idExisted(@PathVariable Long id){
        return bolDetailsRepository.existsById(id);
    }
    public Optional<BolDetails> findById(@PathVariable Long id){
        return bolDetailsRepository.findById(id);
    }
    public Optional<BolDetails> findBySerial(@PathVariable String serial){
        Specification<BolDetails> spec = BolDetailsSpecification.hasSerial(serial);
        return bolDetailsRepository.findOne(spec);
    }
}
