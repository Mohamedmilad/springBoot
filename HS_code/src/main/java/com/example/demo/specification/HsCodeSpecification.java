package com.example.demo.specification;
import org.springframework.data.jpa.domain.Specification;
import com.example.demo.entity.HsCode;

public class HsCodeSpecification {
    public static Specification<HsCode> hasCode(String code) {
        return (root, query, builder) -> builder.equal(root.get("code"), code);
    }
}
