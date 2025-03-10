package br.com.calculo_de_impostos.repositories;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;

public interface TaxRepository {
    TaxResponseDto saveTax(TaxResponseDto taxToCreate);
}