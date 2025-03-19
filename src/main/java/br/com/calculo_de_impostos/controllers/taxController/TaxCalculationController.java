package br.com.calculo_de_impostos.controllers.taxController;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationResponseDto;
import br.com.calculo_de_impostos.services.taxService.TaxCalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impostos")
public class TaxCalculationController {
    @Autowired
    private final TaxCalculationService taxCalculationService;

    public TaxCalculationController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/calculo")
    public ResponseEntity<TaxCalculationResponseDto> taxCalculation(@Valid @RequestBody TaxCalculationRequestDto calculationRequest) {
        TaxCalculationResponseDto calculateTax = taxCalculationService.taxCalculation(calculationRequest);
        return ResponseEntity.ok(calculateTax);
    }
}