package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationResponseDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.SaveTaxRepository;
import br.com.calculo_de_impostos.services.taxService.CreateTaxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CreateTaxServiceImplTest {

    @Mock
    private SaveTaxRepository saveTaxRepository;

    @InjectMocks
    private CreateTaxService createTaxService;

    TaxModel taxModel = new TaxModel();
    TaxCalculationRequestDto taxCalculationRequest = new TaxCalculationRequestDto();

    @Test
    public void testCreateTaxSuccess() {
        String name = "ICMS";
        String description = "ICMS";
        double aliquot = 18.0;

        TaxModel taxToCreate = new TaxModel(name, description, aliquot);
        TaxModel taxCreated = new TaxModel(name, description, aliquot);
        taxCreated.setId(1L);

        Mockito.when(saveTaxRepository.save(taxToCreate)).thenReturn(taxCreated);

        TaxResponseDto taxResponse = createTaxService.createTax(name, description, aliquot);

        Assertions.assertEquals("ICMS", taxResponse.getName());
        Assertions.assertEquals("ICMS", taxResponse.getDescription());
        Assertions.assertEquals(18.0, taxResponse.getAliquot());
    }

    @Test
    public void testCreateTaxWithNullName() {
        String name = null;
        String description = "ICMS";
        double aliquot = 18.0;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createTaxService.createTax(name, description, aliquot);
        });

        Assertions.assertEquals("O nome do imposto deve ser informado.", exception.getMessage());
    }

    @Test
    public void testCreateTaxWithEmptyDescription() {
        String name = "ICMS";
        String description = "";
        double aliquot = 18.0;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createTaxService.createTax(name, description, aliquot);
        });

        Assertions.assertEquals("A descrição do imposto deve ser informada.", exception.getMessage());
    }

    @Test
    public void testCreateTaxWithNegativeAliquot() {
        String name = "ICMS";
        String description = "ICMS";
        double aliquot = -5.0;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createTaxService.createTax(name, description, aliquot);
        });

        Assertions.assertEquals("A alíquota do imposto deve ser maior do que zero.", exception.getMessage());
    }
}