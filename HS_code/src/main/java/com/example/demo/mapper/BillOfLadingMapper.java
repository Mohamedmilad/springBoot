package com.example.demo.mapper;

import com.example.demo.dto.BillOfLadingDtoReq;
import com.example.demo.dto.BillOfLadingDtoRes;
import com.example.demo.entity.BillOfLading;

public class BillOfLadingMapper {
    public static BillOfLadingDtoRes mapToDto(BillOfLading billOfLading) {
        if (billOfLading == null) return null;
        BillOfLadingDtoRes dto = new BillOfLadingDtoRes();

        dto.setNbr(billOfLading.getNbr());
        dto.setAgent(billOfLading.getAgent());
        dto.setId(billOfLading.getId());
        dto.setTimeStamp(billOfLading.getTimeStamp());

        return dto;
    }
    public static BillOfLading mapToEntity(BillOfLadingDtoReq BillOfLadingDtoReq) {
        if (BillOfLadingDtoReq == null) return null;
        BillOfLading billOfLading = new BillOfLading();

        billOfLading.setNbr(BillOfLadingDtoReq.getNbr());

        return billOfLading;
    }
}
