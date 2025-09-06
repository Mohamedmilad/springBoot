package com.example.demo.controller;

import com.example.demo.dto.BillOfLadingDtoReq;
import com.example.demo.dto.BillOfLadingDtoRes;
import com.example.demo.dto.CompaniesDtoReq;
import com.example.demo.dto.CompaniesDtoRes;
import com.example.demo.entity.BillOfLading;
import com.example.demo.entity.Companies;
import com.example.demo.mapper.BillOfLadingMapper;
import com.example.demo.mapper.CompaniesMapper;
import com.example.demo.service.BillOfLadingService;
import com.example.demo.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/BillOfLading")
public class BillOfLadingController {
    private final BillOfLadingService billOfLadingService;
    private final MessageSource messageSource;
    private final CompaniesService companiesService;

    @Autowired
    public BillOfLadingController(BillOfLadingService billOfLadingService, MessageSource messageSource, CompaniesService companiesService) {
        this.billOfLadingService = billOfLadingService;
        this.messageSource = messageSource;
        this.companiesService= companiesService;
    }
    private String msg(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody BillOfLadingDtoReq billOfLadingDtoReq) {
        Optional<Companies> companieId = companiesService.findById(billOfLadingDtoReq.getAgentId());
        if(companieId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",billOfLadingDtoReq.getAgentId()));
        }
        Companies companie=companieId.get();

        BillOfLading billOfLading= BillOfLadingMapper.mapToEntity(billOfLadingDtoReq);
        billOfLading.setAgent(companie);

        if (billOfLading.getNbr() == null || billOfLading.getAgent() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        try{
            BillOfLadingDtoRes bolRes=BillOfLadingMapper.mapToDto(billOfLadingService.save(billOfLading));
            return ResponseEntity.status(HttpStatus.CREATED).body(bolRes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<BillOfLading> billOfLadings = billOfLadingService.getAll();
            List<BillOfLadingDtoRes> result=new ArrayList<>();
            for(BillOfLading billOfLading : billOfLadings) {
                BillOfLadingDtoRes billOfLadingDtoRes= BillOfLadingMapper.mapToDto(billOfLading);
                result.add(billOfLadingDtoRes);
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
        if (!billOfLadingService.idExisted(id)) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        try{
            billOfLadingService.deleteRow(id);
            return ResponseEntity.ok(msg("business.error.delete_success",id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.delete_error",id,e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody BillOfLadingDtoReq billOfLadingDtoReq) {

        Optional<BillOfLading> billOfLadingId = billOfLadingService.findById(id);
        if(billOfLadingId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        BillOfLading billOfLading=billOfLadingId.get();

        if (billOfLadingDtoReq.getNbr() == null && billOfLadingDtoReq.getAgentId() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        if (billOfLadingDtoReq.getNbr() == null ) {
            Optional<Companies> companieId = companiesService.findById(billOfLadingDtoReq.getAgentId());
            if(companieId.isEmpty()){
                return ResponseEntity.status(404).body(msg("business.error.id_not_found",billOfLadingDtoReq.getAgentId()));
            }
            Companies companie=companieId.get();
            billOfLading.setAgent(companie);
            billOfLading.setNbr(billOfLading.getNbr());
        } else if (billOfLadingDtoReq.getAgentId() == null) {
            billOfLading.setAgent(billOfLading.getAgent());
            billOfLading.setNbr(billOfLadingDtoReq.getNbr());
        }
        else {
            Optional<Companies> companieId = companiesService.findById(billOfLadingDtoReq.getAgentId());
            if(companieId.isEmpty()){
                return ResponseEntity.status(404).body(msg("business.error.id_not_found",billOfLadingDtoReq.getAgentId()));
            }
            Companies companie=companieId.get();

            billOfLading.setAgent(companie);
            billOfLading.setNbr(billOfLadingDtoReq.getNbr());
        }
        billOfLading.setChanger_id(billOfLading.getId());
        try{
            BillOfLadingDtoRes billOfLadingDtoRes=BillOfLadingMapper.mapToDto(billOfLadingService.save(billOfLading));
            return ResponseEntity.status(HttpStatus.CREATED).body(billOfLadingDtoRes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.update_error",e.getMessage()));
        }
    }

}