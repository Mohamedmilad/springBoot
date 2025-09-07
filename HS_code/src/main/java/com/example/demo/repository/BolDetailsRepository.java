package com.example.demo.repository;

import com.example.demo.entity.BolDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BolDetailsRepository extends JpaRepository<BolDetails, Long>, JpaSpecificationExecutor<BolDetails> {
}
