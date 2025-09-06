package com.example.demo.service;

import com.example.demo.entity.Companies;
import com.example.demo.repository.CompaniesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesServiceImplementation implements CompaniesService{
    private final CompaniesRepository companiesRepository;

    public CompaniesServiceImplementation(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public Companies save(Companies companies) {
        return companiesRepository.save(companies);
    }

    public List<Companies> getAll(){
        return companiesRepository.findAll();
    }
    public void deleteRow(@PathVariable Long id){
        companiesRepository.deleteById(id);
    }
    public boolean idExisted(@PathVariable Long id){
        return companiesRepository.existsById(id);
    }
    public Optional<Companies> findById(@PathVariable Long id){
        return companiesRepository.findById(id);
    }
}

