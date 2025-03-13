package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.TaxCalculationResponseDto;
import jakarta.validation.Valid;

public interface TaxCalculationService {
    TaxCalculationResponseDto taxCalculation(@Valid TaxCalculationRequestDto calculationRequest);
}