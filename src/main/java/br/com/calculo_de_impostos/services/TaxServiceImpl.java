package br.com.calculo_de_impostos.services;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.repositories.TaxRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public void deleteTaxById(long id) {
        if (!taxRepository.existsById(id)) {
            throw new EntityNotFoundException("O imposto com o ID " + id + " não foi encontrado.");
        }
        taxRepository.deleteTaxById(id);
    }
}