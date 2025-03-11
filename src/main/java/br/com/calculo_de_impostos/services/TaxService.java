package br.com.calculo_de_impostos.services;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import jakarta.validation.Valid;

public interface TaxService {
    TaxResponseDto createTax(String name, String description, double aliquot);
    void deleteTaxById(@Valid long id);
}