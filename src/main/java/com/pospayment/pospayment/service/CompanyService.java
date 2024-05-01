package com.pospayment.pospayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.repository.CompanyRepo;
import com.pospayment.pospayment.util.Converter;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {


    private CompanyRepo companyRepo;

    private Converter converter;

    public CompanyService(CompanyRepo companyRepo, Converter converter) {
        this.companyRepo = companyRepo;
        this.converter = converter;
    }

    public void saveCompany(Company company) {
        companyRepo.save(company);
    }

    public String getCompanyList() throws JsonProcessingException {
        return converter.convertToJson(companyRepo.findAll());
    }
}
