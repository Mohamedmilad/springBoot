package com.example.demo.mapper;

import com.example.demo.dto.BolDetailsDtoReq;
import com.example.demo.dto.BolDetailsDtoRes;
import com.example.demo.entity.BolDetails;

public class BolDetailsMapper {
    public static BolDetailsDtoRes mapToDto(BolDetails bolDetails) {
        if(bolDetails == null) {return null;}
        BolDetailsDtoRes bolDetailsDtoRes = new BolDetailsDtoRes();

        bolDetailsDtoRes.setId(bolDetails.getId());
        bolDetailsDtoRes.setCount(bolDetails.getCount());
        bolDetailsDtoRes.setVolume(bolDetails.getVolume());
        bolDetailsDtoRes.setWeight(bolDetails.getWeight());
        bolDetailsDtoRes.setSerial(bolDetails.getSerial());
        bolDetailsDtoRes.setTimeStamp(bolDetails.getTimeStamp());
        bolDetailsDtoRes.setHsCode(bolDetails.getHsCode());
        bolDetailsDtoRes.setBillOfLading(bolDetails.getBillOfLading());

        return bolDetailsDtoRes;
    }
    public static BolDetails mapToEntity(BolDetailsDtoReq bolDetailsDtoReq) {
        if (bolDetailsDtoReq == null) return null;
        BolDetails bolDetails = new BolDetails();

        bolDetails.setSerial(bolDetailsDtoReq.getSerial());
        bolDetails.setCount(bolDetailsDtoReq.getCount());
        bolDetails.setVolume(bolDetailsDtoReq.getVolume());
        bolDetails.setWeight(bolDetailsDtoReq.getWeight());

        return bolDetails;
    }
}
