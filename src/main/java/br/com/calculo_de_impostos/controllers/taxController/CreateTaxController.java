package br.com.calculo_de_impostos.controllers.taxController;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.taxService.CreateTaxService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impostos")
public class CreateTaxController {
    @Autowired
    private final CreateTaxService createTaxService;

    public CreateTaxController(CreateTaxService createTaxService){
        this.createTaxService = createTaxService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/tipos")
    public ResponseEntity<TaxResponseDto> createTax (@Valid @RequestBody TaxRequestDto taxRequest) {
        TaxResponseDto taxResponse = createTaxService.createTax(taxRequest.getName(), taxRequest.getDescription(), taxRequest.getAliquot());
        return ResponseEntity.status(HttpStatus.CREATED).body(taxResponse);
    }
}