package com.pospayment.pospayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.repository.CompanyRepo;
import com.pospayment.pospayment.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {


    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private JsonConverter jsonConverter;

    public void saveCompany(Company company) {
        companyRepo.save(company);
    }

    public String getCompanyList() throws JsonProcessingException {
        return jsonConverter.convertToJson(companyRepo.findAll());
    }
}
