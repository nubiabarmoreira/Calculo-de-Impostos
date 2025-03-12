package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface saveTaxRepository {
    TaxResponseDto saveTax(TaxResponseDto taxToCreate);
    void deleteTaxById(long id);
}