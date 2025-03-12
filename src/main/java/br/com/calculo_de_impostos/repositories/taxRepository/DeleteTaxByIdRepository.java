package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteTaxByIdRepository extends JpaRepository<TaxModel, Long> {
    void deleteById(long id);
}