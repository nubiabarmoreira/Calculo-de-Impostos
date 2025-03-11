package br.com.calculo_de_impostos.repositories;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.stereotype.Repository;

@Repository
public class TaxRepositoryImpl implements TaxRepository {
    private final DatabaseTaxRepository databaseTaxRepository;

    public TaxRepositoryImpl (DatabaseTaxRepository databaseTaxRepository){
        this.databaseTaxRepository = databaseTaxRepository;
    }

    @Override
    public TaxResponseDto saveTax(TaxResponseDto taxToCreate) {
        TaxModel taxToSave = new TaxModel(taxToCreate.getName(), taxToCreate.getDescription(), taxToCreate.getAliquot());
        TaxModel taxSaved = databaseTaxRepository.save(taxToSave);

        return new TaxResponseDto(taxSaved.getId(), taxSaved.getName(), taxSaved.getDescription(), taxSaved.getAliquot());
    }
}
