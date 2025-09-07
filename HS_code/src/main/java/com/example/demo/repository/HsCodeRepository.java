package com.example.demo.repository;

import com.example.demo.entity.HsCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HsCodeRepository extends JpaRepository<HsCode, Long>, JpaSpecificationExecutor<HsCode> {
}
