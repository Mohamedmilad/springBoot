package com.example.demo.controller;

import com.example.demo.dto.BillOfLadingDtoReq;
import com.example.demo.dto.BillOfLadingDtoRes;
import com.example.demo.dto.BolDetailsDtoReq;
import com.example.demo.dto.BolDetailsDtoRes;
import com.example.demo.entity.BillOfLading;
import com.example.demo.entity.BolDetails;
import com.example.demo.entity.Companies;
import com.example.demo.entity.HsCode;
import com.example.demo.mapper.BillOfLadingMapper;
import com.example.demo.mapper.BolDetailsMapper;
import com.example.demo.service.BillOfLadingService;
import com.example.demo.service.BolDetailsService;
import com.example.demo.service.HsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/BolDetails")
public class BolDetailsController {
    private final HsCodeService hsCodeService;
    private final BillOfLadingService billOfLadingService;
    private final BolDetailsService bolDetailsService;
    private final MessageSource messageSource;

    @Autowired
    public BolDetailsController(BolDetailsService bolDetailsService , MessageSource messageSource, HsCodeService hsCodeService, BillOfLadingService billOfLadingService) {
        this.hsCodeService = hsCodeService;
        this.billOfLadingService = billOfLadingService;
        this.bolDetailsService = bolDetailsService;
        this.messageSource = messageSource;
    }
    private String msg(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody BolDetailsDtoReq bolDetailsDtoReq) {
        BolDetails bolDetails= BolDetailsMapper.mapToEntity(bolDetailsDtoReq);
        if (bolDetailsDtoReq.getHsCodeId() == null || bolDetailsDtoReq.getBillOfLadingId() == null || bolDetails.getVolume() == null || bolDetails.getWeight() == null || bolDetails.getCount() == null ||bolDetails.getSerial() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        Optional<HsCode> hsCodeId=hsCodeService.findById(bolDetailsDtoReq.getHsCodeId());
        if(hsCodeId.isEmpty()){

            return ResponseEntity.status(404).body(msg("business.error.id_not_found",bolDetailsDtoReq.getHsCodeId(),"hs_code"));
        }
        HsCode hsCode=hsCodeId.get();
        Optional<BillOfLading> billOfLadingId=billOfLadingService.findById(bolDetailsDtoReq.getBillOfLadingId());
        if(billOfLadingId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",bolDetailsDtoReq.getBillOfLadingId(),"bill_of_lading"));
        }
        BillOfLading billOfLading=billOfLadingId.get();
        bolDetails.setHsCode(hsCode);
        bolDetails.setBillOfLading(billOfLading);

        try{
            BolDetailsDtoRes bolRes=BolDetailsMapper.mapToDto(bolDetailsService.save(bolDetails));
            return ResponseEntity.status(HttpStatus.CREATED).body(bolRes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<BolDetails> bolDetails = bolDetailsService.getAll();
            List<BolDetailsDtoRes> result=new ArrayList<>();
            for(BolDetails bolDetail : bolDetails) {
                BolDetailsDtoRes bolDetailsDtoRes= BolDetailsMapper.mapToDto(bolDetail);
                result.add(bolDetailsDtoRes);
            }
            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg("business.error.table_empty","BolDetails")); //404 not 204 to add a content
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        if (!bolDetailsService.idExisted(id)) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id,"BolDetails"));
        }
        try{
            bolDetailsService.deleteRow(id);
            return ResponseEntity.ok(msg("business.error.delete_success",id,"BolDetails"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.delete_error",id,"BolDetails",e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody BolDetailsDtoReq bolDetailsDtoReq) {

        Optional<BolDetails> bolDetailsId = bolDetailsService.findById(id);
        if(bolDetailsId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id,"BolDetails"));
        }
        BolDetails bolDetails=bolDetailsId.get();

        if (bolDetailsDtoReq.getCount() == null && bolDetailsDtoReq.getSerial() == null && bolDetailsDtoReq.getWeight() == null && bolDetailsDtoReq.getVolume() == null && bolDetailsDtoReq.getBillOfLadingId() == null && bolDetailsDtoReq.getHsCodeId() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }

        if (bolDetailsDtoReq.getCount() != null) {
            bolDetails.setCount(bolDetailsDtoReq.getCount());
        }
        if (bolDetailsDtoReq.getVolume() != null) {
            bolDetails.setVolume(bolDetailsDtoReq.getVolume());
        }
        if (bolDetailsDtoReq.getWeight() != null) {
            bolDetails.setWeight(bolDetailsDtoReq.getWeight());
        }
        if (bolDetailsDtoReq.getSerial() != null) {
            bolDetails.setSerial(bolDetailsDtoReq.getSerial());
        }
        if (bolDetailsDtoReq.getHsCodeId() != null) {
            Optional<HsCode> hsCodeId = hsCodeService.findById(bolDetailsDtoReq.getHsCodeId());
            if(hsCodeId.isEmpty()){
                return ResponseEntity.status(404).body(msg("business.error.id_not_found",bolDetailsDtoReq.getHsCodeId(),"hs_code"));
            }
            HsCode hsCode=hsCodeId.get();
            bolDetails.setHsCode(hsCode);
        }
        if (bolDetailsDtoReq.getBillOfLadingId() != null) {
            Optional<BillOfLading> billOfLadingId = billOfLadingService.findById(bolDetailsDtoReq.getBillOfLadingId());
            if(billOfLadingId.isEmpty()){
                return ResponseEntity.status(404).body(msg("business.error.id_not_found",bolDetailsDtoReq.getBillOfLadingId(),"bill_of_lading"));
            }
            BillOfLading billOfLading=billOfLadingId.get();
            bolDetails.setBillOfLading(billOfLading);
        }
        bolDetails.setChanger_id(bolDetails.getId());

        try{
            BolDetailsDtoRes bolDetailsDtoRes=BolDetailsMapper.mapToDto(bolDetailsService.save(bolDetails));
            return ResponseEntity.status(HttpStatus.CREATED).body(bolDetailsDtoRes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.update_error","BolDetails",e.getMessage()));
        }
    }
    @GetMapping("/getBySerial/{serial}")
    public ResponseEntity<?> getBySerial(@PathVariable String serial) {
        try{
            Optional<BolDetails> bolDetails=bolDetailsService.findBySerial(serial);
            if(bolDetails.isEmpty()){
                return ResponseEntity.status(404).body(msg("business.error.serial_not_found",serial,"BolDetails"));
            }
            return  ResponseEntity.ok(bolDetails);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }
}