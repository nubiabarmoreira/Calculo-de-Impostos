package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FindTaxByIdRepository extends JpaRepository<TaxModel, Long> {
    TaxModel findById(long id);
}