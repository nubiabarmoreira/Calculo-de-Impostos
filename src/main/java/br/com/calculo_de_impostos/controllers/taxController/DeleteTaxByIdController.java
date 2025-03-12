package br.com.calculo_de_impostos.controllers.taxController;

import br.com.calculo_de_impostos.services.taxService.DeleteTaxByIdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impostos")
public class DeleteTaxByIdController {
    @Autowired
    private final DeleteTaxByIdService deleteTaxByIdService;

    public DeleteTaxByIdController(DeleteTaxByIdService deleteTaxByIdService){
        this.deleteTaxByIdService = deleteTaxByIdService;
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> deleteTaxById (@Valid @PathVariable long id){
        deleteTaxByIdService.deleteTaxById(id);
        return ResponseEntity.noContent().build();
    }
}