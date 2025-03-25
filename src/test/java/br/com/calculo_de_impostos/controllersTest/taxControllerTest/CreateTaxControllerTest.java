package br.com.calculo_de_impostos.controllersTest.taxControllerTest;

import br.com.calculo_de_impostos.controllers.taxController.CreateTaxController;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.services.taxService.CreateTaxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class CreateTaxControllerTest {

    @Mock
    private CreateTaxService createTaxService;

    @InjectMocks
    private CreateTaxController createTaxController;

    private TaxRequestDto taxRequest;
    private TaxResponseDto taxResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    public void testCreateTaxWithNullName() {
        TaxRequestDto taxRequest = new TaxRequestDto(null, "ICMS", 18.0);
        TaxResponseDto taxResponse = new TaxResponseDto(null, "ICMS", 18.0);

        Mockito.when(createTaxService.createTax(null, "ICMS", 18.0))
                .thenThrow(new IllegalArgumentException("O nome do imposto deve ser informado."));

        Exception exception = Assertions
                .assertThrows(IllegalArgumentException.class,
                        () -> createTaxController.createTax(taxRequest));

        Assertions.assertEquals("O nome do imposto deve ser informado.", exception.getMessage());
    }

    @Test
    public void testCreateTaxWithEmptyDescription() {
        taxRequest.setName("ICMS");
        taxRequest.setDescription(" ");
        taxRequest.setAliquot(18.0);

        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> createTaxController.createTax(taxRequest));

        Assertions.assertEquals("A descrição do imposto deve ser informada.", exception.getMessage());
    }

    @Test
    public void testCreateTaxWithNegativeAliquot() {
        taxRequest.setName("ICMS");
        taxRequest.setDescription("ICMS");
        taxRequest.setAliquot(-5.0);

        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> createTaxController.createTax(taxRequest));

        Assertions.assertEquals("A alíquota do imposto deve ser maior do que zero.", exception.getMessage());
    }
}