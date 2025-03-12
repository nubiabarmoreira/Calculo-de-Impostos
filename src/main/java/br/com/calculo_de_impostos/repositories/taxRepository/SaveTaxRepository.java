package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveTaxRepository extends JpaRepository<TaxModel, Long> {
    TaxResponseDto save(TaxResponseDto taxToCreate);
}