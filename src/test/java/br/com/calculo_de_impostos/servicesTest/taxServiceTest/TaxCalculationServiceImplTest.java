package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.TaxCalculationRepository;
import br.com.calculo_de_impostos.services.taxService.TaxCalculationService;
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
public class TaxCalculationServiceImplTest {

    @Mock
    private TaxCalculationRepository taxCalculationRepository;

    @InjectMocks
    private TaxCalculationService taxCalculationService;

    TaxModel taxModel = new TaxModel();
    TaxCalculationRequestDto taxCalculationRequest = new TaxCalculationRequestDto();

    @Test
    public void testTaxCalculationSuccess() {
        //arrange
        taxModel.setId(1L);
        taxModel.setName("ICMS");
        taxModel.setAliquot(18.0);

        Mockito.when(taxCalculationRepository.findById(1L)).thenReturn(taxModel);

        taxCalculationRequest.setTaxTypeId(1L);
        taxCalculationRequest.setBaseValue(1000.0);

        //act
        TaxCalculationResponseDto taxCalculationResponse = taxCalculationService.taxCalculation(taxCalculationRequest);

        //assert
        Assertions.assertEquals("ICMS", taxCalculationResponse.getTaxTypeName());
        Assertions.assertEquals(1000.0, taxCalculationResponse.getBaseValue());
        Assertions.assertEquals(18.0, taxCalculationResponse.getAliquot());
        Assertions.assertEquals(180.0, taxCalculationResponse.getTaxValue());
    }
}