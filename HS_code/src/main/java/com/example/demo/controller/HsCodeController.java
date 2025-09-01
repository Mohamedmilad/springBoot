package com.example.demo.controller;
import com.example.demo.service.HsCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.HsCode;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hs")

public class HsCodeController {
    private final HsCodeService hsCodeService;

    public HsCodeController(HsCodeService hsCodeService) {
        this.hsCodeService = hsCodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHsCode(@RequestBody HsCode hsCode) {
        if (hsCode.getCode() == null || hsCode.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code and name cannot be NULL");
        }
        try{
            HsCode hs=hsCodeService.save(hsCode);
            return ResponseEntity.status(HttpStatus.CREATED).body(hs);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding element"+ e.getMessage());
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        try {
            List<HsCode> hsCodes = hsCodeService.getAll();
            if (hsCodes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rows found in hsCode"); //404 not 204 to add a content
            }
            return ResponseEntity.ok(hsCodes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching the table" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRow(@PathVariable Long id) {
        if (!hsCodeService.idExisted(id)) {
            return ResponseEntity.status(404).body("Row with ID=" + id + " doesn't exist in 'hs_code'");
        }
        try{
            hsCodeService.deleteRow(id);
            return ResponseEntity.ok("Row with ID=" + id + " in 'hs_code' deleted successfully!");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting row with ID=" + id + " in 'hs_code': " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTable(@PathVariable Long id, @RequestBody HsCode hsCode) {

        Optional<HsCode> hsId = hsCodeService.findById(id);
        if(hsId.isEmpty()){
            return ResponseEntity.status(404).body("Row with ID=" + id + " doesn't exist in 'hs_code'");
        }
        HsCode hs=hsId.get();
        if (hsCode.getCode() == null && hsCode.getName() == null) {
            return ResponseEntity.badRequest().body("Code and name cannot be NULL");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating element"+ e.getMessage());
        }
    }
}
