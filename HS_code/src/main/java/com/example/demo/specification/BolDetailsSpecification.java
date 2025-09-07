package com.example.demo.specification;

import com.example.demo.entity.BolDetails;
import com.example.demo.entity.HsCode;
import org.springframework.data.jpa.domain.Specification;

public class BolDetailsSpecification {
    public static Specification<BolDetails> hasSerial(String serial) {
        return (root, query, builder) -> builder.equal(root.get("serial"), serial);
    }
}
