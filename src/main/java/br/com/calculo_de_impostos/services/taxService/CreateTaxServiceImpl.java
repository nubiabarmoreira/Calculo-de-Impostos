package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.SaveTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaxServiceImpl implements CreateTaxService {
    @Autowired
    private SaveTaxRepository saveTaxRepository;

    public CreateTaxServiceImpl(SaveTaxRepository saveTaxRepository){
        this.saveTaxRepository = saveTaxRepository;
    }

    @Override
    public TaxResponseDto createTax(String name, String description, double aliquot) {
        TaxModel taxToCreate = new TaxModel(name, description, aliquot);
        TaxModel taxCreated = saveTaxRepository.save(taxToCreate);

        return new TaxResponseDto(taxCreated.getName(), taxCreated.getDescription(), taxCreated.getAliquot());
    }
}