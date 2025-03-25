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
        if (name.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("O nome do imposto deve ser informado.");
        }
        if (description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição do imposto deve ser informada.");
        }
        if (aliquot <= 0) {
            throw new IllegalArgumentException("A alíquota do imposto deve ser maior do que zero.");
        }

        TaxModel taxToCreate = new TaxModel(name, description, aliquot);
        TaxModel taxCreated = saveTaxRepository.save(taxToCreate);

        return new TaxResponseDto(taxCreated.getName(), taxCreated.getDescription(), taxCreated.getAliquot());
    }
}