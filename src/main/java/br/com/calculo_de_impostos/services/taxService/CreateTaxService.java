package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;

public interface CreateTaxService {
    TaxResponseDto createTax(String name, String description, double aliquot);
}