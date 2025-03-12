package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import jakarta.validation.Valid;

public interface CreateTaxService {
    TaxResponseDto createTax(String name, String description, double aliquot);
}