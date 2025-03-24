package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationResponseDto;
import jakarta.validation.Valid;

public interface TaxCalculationService {
    TaxCalculationResponseDto taxCalculation(@Valid TaxCalculationRequestDto calculationRequest);
}