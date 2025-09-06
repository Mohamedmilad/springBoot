package com.example.demo.mapper;

import com.example.demo.dto.HsCodeDtoReq;
import com.example.demo.dto.HsCodeDtoRes;
import com.example.demo.entity.HsCode;

public class HsCodeMapper {
    public static HsCodeDtoRes mapToDto(HsCode hsCode) {
        if (hsCode == null) return null;
        HsCodeDtoRes dto =new HsCodeDtoRes();

        dto.setId(hsCode.getId());
        dto.setCode(hsCode.getCode());
        dto.setName(hsCode.getName());
        dto.setTimeStamp(hsCode.getTimeStamp());

        return dto;
    }

    public static HsCode mapToEntity(HsCodeDtoReq hsCodeDto) {
        if (hsCodeDto == null) return null;
        HsCode hsCode = new HsCode();

        hsCode.setCode(hsCodeDto.getCode());
        hsCode.setName(hsCodeDto.getName());

        return hsCode;
    }
}