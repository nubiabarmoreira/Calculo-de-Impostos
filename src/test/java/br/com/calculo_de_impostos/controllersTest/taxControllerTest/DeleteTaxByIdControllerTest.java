package br.com.calculo_de_impostos.controllersTest.taxControllerTest;

import br.com.calculo_de_impostos.controllers.taxController.DeleteTaxByIdController;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxRequestDto;
import br.com.calculo_de_impostos.services.taxService.DeleteTaxByIdService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DeleteTaxByIdControllerTest {

    @Mock
    private DeleteTaxByIdService deleteTaxByIdService;

    @InjectMocks
    private DeleteTaxByIdController deleteTaxByIdController;

    TaxRequestDto taxRequest = new TaxRequestDto();

    @Test
    public void testDeleteTaxByIdControllerSuccess() {
        long id = 1L;

        Mockito.doNothing().when(deleteTaxByIdService).deleteTaxById(id);

        ResponseEntity<Void> response = deleteTaxByIdController.deleteTaxById(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(deleteTaxByIdService, Mockito.times(1)).deleteTaxById(id);
    }
}