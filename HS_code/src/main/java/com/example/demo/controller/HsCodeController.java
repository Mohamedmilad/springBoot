package com.example.demo.controller;
import com.example.demo.service.HsCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.HsCode;
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
    public ResponseEntity<?> addHsCode(@RequestBody HsCode hsCode) {
        if (hsCode.getCode() == null || hsCode.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        try{
            HsCode hs=hsCodeService.save(hsCode);
            return ResponseEntity.status(HttpStatus.CREATED).body(hs);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<HsCode> hsCodes = hsCodeService.getAll();
            if (hsCodes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg("business.error.table_empty")); //404 not 204 to add a content
            }
            return ResponseEntity.ok(hsCodes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.invalid_request",e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        if (!hsCodeService.idExisted(id)) {
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        try{
            hsCodeService.deleteRow(id);
            return ResponseEntity.ok(msg("business.error.delete_success",id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.delete_error",id,e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody HsCode hsCode) {

        Optional<HsCode> hsId = hsCodeService.findById(id);
        if(hsId.isEmpty()){
            return ResponseEntity.status(404).body(msg("business.error.id_not_found",id));
        }
        HsCode hs=hsId.get();
        if (hsCode.getCode() == null && hsCode.getName() == null) {
            return ResponseEntity.badRequest().body(msg("business.error.cannot_Null"));
        }
        if (hsCode.getCode() == null ) {
            hs.setName(hsCode.getName());
            hs.setCode(hs.getCode());
        } else if (hsCode.getName() == null) {
            hs.setName(hs.getName());
            hs.setCode(hsCode.getCode());
        }
        else {
            hs.setName(hsCode.getName());
            hs.setCode(hsCode.getCode());
        }
        try{
            HsCode hs_Code=hsCodeService.save(hs);
            return ResponseEntity.status(HttpStatus.CREATED).body(hs_Code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg("business.error.update_error",e.getMessage()));
        }
    }
}
