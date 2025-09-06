package com.example.demo.service;

import com.example.demo.entity.BillOfLading;
import com.example.demo.repository.BillOfLadingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BillOfLadingServiceImplementation implements BillOfLadingService {
    private final BillOfLadingRepository billOfLadingRepository;

    public BillOfLadingServiceImplementation(BillOfLadingRepository billOfLadingRepository) {
        this.billOfLadingRepository = billOfLadingRepository;
    }

    public BillOfLading save(BillOfLading companies) {
        return billOfLadingRepository.save(companies);
    }

    public List<BillOfLading> getAll(){
        return billOfLadingRepository.findAll();
    }
    public void deleteRow(@PathVariable Long id){
        billOfLadingRepository.deleteById(id);
    }
    public boolean idExisted(@PathVariable Long id){
        return billOfLadingRepository.existsById(id);
    }
    public Optional<BillOfLading> findById(@PathVariable Long id){
        return billOfLadingRepository.findById(id);
    }
}
