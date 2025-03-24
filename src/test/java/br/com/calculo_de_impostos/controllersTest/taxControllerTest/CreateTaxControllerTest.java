package br.com.calculo_de_impostos.controllersTest.taxControllerTest;

import br.com.calculo_de_impostos.controllers.taxController.CreateTaxController;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.taxService.CreateTaxService;
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
public class CreateTaxControllerTest {

    @Mock
    private CreateTaxService createTaxService;

    @InjectMocks
    private CreateTaxController createTaxController;

    TaxRequestDto taxRequest = new TaxRequestDto();

    @Test
    public void testCreateTaxSuccess() {
        TaxRequestDto taxRequest = new TaxRequestDto("ICMS", "ICMS", 18.0);
        TaxResponseDto taxResponse = new TaxResponseDto("ICMS", "ICMS", 18.0);

        Mockito.when(createTaxService.createTax(taxRequest.getName(), taxRequest.getDescription(), taxRequest.getAliquot()))
                .thenReturn(taxResponse);

        ResponseEntity<TaxResponseDto> response = createTaxController.createTax(taxRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(taxResponse, response.getBody());
    }
}