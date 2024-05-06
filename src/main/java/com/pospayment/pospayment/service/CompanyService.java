package com.pospayment.pospayment.service;

import com.pospayment.pospayment.dto.CompanyListDTO;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.repository.CompanyRepo;
import com.pospayment.pospayment.util.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    public String getCompanyList() {
        List<CompanyListDTO> companyList = new ArrayList<>();
        List<Company> companies = companyRepo.findAll();

        for (Company company : companies) {
            companyList.add(converter.convertToDTO(company, CompanyListDTO.class));
        }

        return converter.convertToJson(companyList);
    }
}
