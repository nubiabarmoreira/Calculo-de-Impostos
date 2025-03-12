package br.com.calculo_de_impostos.repositories.taxRepository;

import br.com.calculo_de_impostos.models.TaxModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindAllTaxRepository extends JpaRepository<TaxModel, Long> {
    List<TaxModel> findAll();
}