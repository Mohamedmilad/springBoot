package com.example.demo.mapper;

import com.example.demo.dto.HsCodeDto;
import com.example.demo.entity.HsCode;

public class HsCodeMapper {
    public static HsCodeDto mapToDto(HsCode hsCode) {
        if (hsCode == null) return null;
        HsCodeDto dto =new HsCodeDto();

        dto.setId(hsCode.getId());
        dto.setCode(hsCode.getCode());
        dto.setName(hsCode.getName());
        dto.setTimeStamp(hsCode.getTimestamp());

        return dto;
    }

    public static HsCode mapToEntity(HsCodeDto hsCodeDto) {
        if (hsCodeDto == null) return null;
        HsCode hsCode = new HsCode();

        hsCode.setId(hsCodeDto.getId());
        hsCode.setCode(hsCodeDto.getCode());
        hsCode.setName(hsCodeDto.getName());
        hsCode.setTimestamp(hsCodeDto.getTimeStamp());

        return hsCode;
    }
}