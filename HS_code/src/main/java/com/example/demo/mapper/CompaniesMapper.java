package com.example.demo.mapper;

import com.example.demo.dto.CompaniesDtoReq;
import com.example.demo.dto.CompaniesDtoRes;
import com.example.demo.entity.Companies;

public class CompaniesMapper {
    public static CompaniesDtoRes mapToDto(Companies companies){
        if (companies == null) return null;
        CompaniesDtoRes companiesDtoRes = new CompaniesDtoRes();

        companiesDtoRes.setTimeStamp(companies.getTimeStamp());
        companiesDtoRes.setId(companies.getId());
        companiesDtoRes.setName(companies.getName());
        companiesDtoRes.setTaxNumber(companies.getTaxNumber());
//        companiesDtoRes.setBillsOfLading(companies.getBillsOfLading());

        return companiesDtoRes;
    }
    public static Companies mapToEntity(CompaniesDtoReq CompaniesReq) {
        if (CompaniesReq == null) return null;
        Companies companies = new Companies();

        companies.setTaxNumber(CompaniesReq.getTaxNumber());
        companies.setName(CompaniesReq.getName());

        return companies;

    }

}
