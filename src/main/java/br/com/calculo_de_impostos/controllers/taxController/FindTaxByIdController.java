package br.com.calculo_de_impostos.controllers.taxController;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.taxService.FindTaxByIdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impostos")
public class FindTaxByIdController {
    @Autowired
    private final FindTaxByIdService findTaxByIdService;

    public FindTaxByIdController(FindTaxByIdService findTaxByIdService) {
        this.findTaxByIdService = findTaxByIdService;
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<TaxResponseDto> findTaxById(@Valid @PathVariable Long id) {
        TaxResponseDto taxById = findTaxByIdService.findTaxById(id);
        return ResponseEntity.ok(taxById);
    }
}