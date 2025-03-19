package br.com.calculo_de_impostos.controllers.taxController;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.taxService.FindAllTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/impostos")
public class FindAllTaxController {
    @Autowired
    private final FindAllTaxService findAllTaxService;

    public FindAllTaxController(FindAllTaxService findAllTaxService) {
        this.findAllTaxService = findAllTaxService;
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<TaxResponseDto>> findAllTax(){
        List<TaxResponseDto> allTax = findAllTaxService.findAllTax();
        return ResponseEntity.ok(allTax);
    }
}