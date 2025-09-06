package com.example.demo.controller;

import com.example.demo.dto.CompaniesDtoReq;
import com.example.demo.dto.CompaniesDtoRes;
import com.example.demo.entity.Companies;
import com.example.demo.mapper.CompaniesMapper;
import com.example.demo.service.CompaniesService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private final CompaniesService companiesService;
    private final MessageSource messageSource;

    public CompaniesController(CompaniesService companiesService, MessageSource messageSource) {
        this.companiesService = companiesService;
        this.messageSource = messageSource;
    }

    private String msg(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CompaniesDtoReq companiesDtoReq) {
        Companies companies= CompaniesMapper.mapToEntity(companiesDtoReq);
        if (companies.getTaxNumber() == null || companies.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        try{
            CompaniesDtoRes hs=CompaniesMapper.mapToDto(companiesService.save(companies));
            return ResponseEntity.status(HttpStatus.CREATED).body(hs);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<Companies> companies = companiesService.getAll();
            List<CompaniesDtoRes> result=new ArrayList<>();
            for(Companies companie : companies) {
                CompaniesDtoRes companiesDtoRes=CompaniesMapper.mapToDto(companie);
                result.add(companiesDtoRes);
            }

            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg("business.error.table_empty")); //404 not 204 to add a content
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        if (!companiesService.idExisted(id)) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        try{
            companiesService.deleteRow(id);
            return ResponseEntity.ok(msg("business.error.delete_success",id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.delete_error",id,e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody CompaniesDtoReq companiesDtoReq) {

        Optional<Companies> compaiesId = companiesService.findById(id);
        if(compaiesId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        Companies companie=compaiesId.get();

        if (companiesDtoReq.getTaxNumber() == null && companiesDtoReq.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }

        if (companiesDtoReq.getTaxNumber() == null ) {
            companie.setName(companiesDtoReq.getName());
            companie.setTaxNumber(companie.getTaxNumber());
        } else if (companiesDtoReq.getName() == null) {
            companie.setName(companie.getName());
            companie.setTaxNumber(companiesDtoReq.getTaxNumber());
        }
        else {
            companie.setName(companiesDtoReq.getName());
            companie.setTaxNumber(companiesDtoReq.getTaxNumber());
        }
        companie.setChanger_id(companie.getId());
        try{
            CompaniesDtoRes companiesRes=CompaniesMapper.mapToDto(companiesService.save(companie));
            return ResponseEntity.status(HttpStatus.CREATED).body(companiesRes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.update_error",e.getMessage()));
        }
    }
}
