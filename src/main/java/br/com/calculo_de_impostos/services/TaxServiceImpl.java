package br.com.calculo_de_impostos.services;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceImpl implements TaxService {
    @Autowired
    private final TaxRepository taxRepository;

    public TaxServiceImpl(TaxRepository taxRepository){
        this.taxRepository = taxRepository;
    }

    @Override
    public TaxResponseDto createTax(String name, String description, double aliquot) {
        TaxResponseDto taxToCreate = new TaxResponseDto(name, description, aliquot);
        TaxResponseDto taxCreated = taxRepository.saveTax(taxToCreate);

        return new TaxResponseDto(taxCreated.getName(), taxCreated.getDescription(), taxCreated.getAliquot());
    }
}