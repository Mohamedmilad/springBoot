package com.example.demo.controller;
import com.example.demo.dto.HsCodeDtoReq;
import com.example.demo.dto.HsCodeDtoRes;
import com.example.demo.mapper.HsCodeMapper;
import com.example.demo.service.HsCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.HsCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import java.util.Locale;

@RestController
@RequestMapping("/hs")
public class HsCodeController {
    private final HsCodeService hsCodeService;
    private final MessageSource messageSource;

    public HsCodeController(HsCodeService hsCodeService, MessageSource messageSource) {
        this.hsCodeService = hsCodeService;
        this.messageSource = messageSource;
    }

    private String msg(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHsCode(@RequestBody HsCodeDtoReq hsCodeDto) {
        HsCode hsCode= HsCodeMapper.mapToEntity(hsCodeDto);
        if (hsCode.getCode() == null || hsCode.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        try{
            HsCodeDtoRes hs=HsCodeMapper.mapToDto(hsCodeService.save(hsCode));
            return ResponseEntity.status(HttpStatus.CREATED).body(hs);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<HsCode> hsCodes = hsCodeService.getAll();
            List<HsCodeDtoRes> result=new ArrayList<>();
            for(HsCode hsCode : hsCodes) {
                HsCodeDtoRes hsCodeDto=HsCodeMapper.mapToDto(hsCode);
                result.add(hsCodeDto);
            }

            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg("business.error.table_empty","Hs_Code")); //404 not 204 to add a content
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        if (!hsCodeService.idExisted(id)) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id,"Hs_Code"));
        }
        try{
            hsCodeService.deleteRow(id);
            return ResponseEntity.ok(msg("business.error.delete_success",id,"Hs_Code"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.delete_error",id,"Hs_Code",e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody HsCodeDtoReq hsCodeDto) {

        Optional<HsCode> hsId = hsCodeService.findById(id);
        if(hsId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id,"Hs_Code"));
        }
        HsCode hs=hsId.get();
        if (hsCodeDto.getCode() == null && hsCodeDto.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        if (hsCodeDto.getCode() == null ) {
            hs.setName(hsCodeDto.getName());
            hs.setCode(hs.getCode());
        } else if (hsCodeDto.getName() == null) {
            hs.setName(hs.getName());
            hs.setCode(hsCodeDto.getCode());
        }
        else {
            hs.setName(hsCodeDto.getName());
            hs.setCode(hsCodeDto.getCode());
        }
        hs.setChanger_id(hs.getId());
        try{
            HsCodeDtoRes hs_Code=HsCodeMapper.mapToDto(hsCodeService.save(hs));
            return ResponseEntity.status(HttpStatus.CREATED).body(hs_Code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.update_error","Hs_Code",e.getMessage()));
        }
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) {
        try{
            Optional<HsCode> hs_code=hsCodeService.findByCode(code);
            return  ResponseEntity.ok(hs_code);
        }catch (Exception e) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",code,"Hs_Code"));
        }
    }

}
