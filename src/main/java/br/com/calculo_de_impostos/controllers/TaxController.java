package br.com.calculo_de_impostos.controllers;

import br.com.calculo_de_impostos.dtos.TaxRequestDto;
import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.TaxService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impostos")
public class TaxController {
    @Autowired
    private TaxService taxService;

    public TaxController(TaxService taxService){
        this.taxService = taxService;
    }

  //  @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tipos")
    public ResponseEntity<TaxResponseDto> createTax (@Valid @RequestBody TaxRequestDto taxRequest) {
        TaxResponseDto taxResponse = taxService.createTax(taxRequest.getName(), taxRequest.getDescription(), taxRequest.getAliquot());
        return ResponseEntity.status(HttpStatus.CREATED).body(taxResponse);
    }
}