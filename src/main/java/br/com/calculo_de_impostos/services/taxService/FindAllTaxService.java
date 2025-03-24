package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;

import java.util.List;

public interface FindAllTaxService {
    List<TaxResponseDto> findAllTax();
}
