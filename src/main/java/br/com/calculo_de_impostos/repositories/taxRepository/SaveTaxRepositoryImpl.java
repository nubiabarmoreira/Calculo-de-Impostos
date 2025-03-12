package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class TaxRepositoryImpl implements TaxRepository {
//    @Autowired
//    private final DatabaseTaxRepository databaseTaxRepository;
//
//    public TaxRepositoryImpl (DatabaseTaxRepository databaseTaxRepository){
//        this.databaseTaxRepository = databaseTaxRepository;
//    }

    @Override
    public TaxResponseDto saveTax(TaxResponseDto taxToCreate) {
        TaxModel taxToSave = new TaxModel(taxToCreate.getName(), taxToCreate.getDescription(), taxToCreate.getAliquot());
        TaxModel taxSaved = databaseTaxRepository.save(taxToSave);

        return new TaxResponseDto(taxSaved.getId(), taxSaved.getName(), taxSaved.getDescription(), taxSaved.getAliquot());
    }

    @Override
    public void deleteTaxById(long id) {
        if (!databaseTaxRepository.findById(id)) {
            throw new EntityNotFoundException("O imposto com o ID " + id + " não foi encontrado.");
        }
        databaseTaxRepository.deleteById(id);
    }
}
