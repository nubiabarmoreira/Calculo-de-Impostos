package br.com.calculo_de_impostos.services;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;

public interface TaxService {
    TaxResponseDto createTax(String name, String description, double aliquot);
}