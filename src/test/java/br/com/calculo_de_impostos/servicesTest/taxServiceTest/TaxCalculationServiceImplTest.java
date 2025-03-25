package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.taxDtos.TaxCalculationResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.TaxCalculationRepository;
import br.com.calculo_de_impostos.services.taxService.TaxCalculationServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class TaxCalculationServiceImplTest {

    @Mock
    private TaxCalculationRepository taxCalculationRepository;

    @InjectMocks
    private TaxCalculationServiceImpl taxCalculationService;

    TaxModel taxModel = new TaxModel();
    TaxCalculationRequestDto taxCalculationRequest = new TaxCalculationRequestDto();

    @Test
    public void testTaxCalculationSuccess() {
        taxModel.setId(1L);
        taxModel.setName("ICMS");
        taxModel.setAliquot(18.0);

        Mockito.when(taxCalculationRepository.findById(1L)).thenReturn(taxModel);

        taxCalculationRequest.setTaxTypeId(1L);
        taxCalculationRequest.setBaseValue(1000.0);

        TaxCalculationResponseDto taxCalculationResponse = taxCalculationService.taxCalculation(taxCalculationRequest);

        Assertions.assertEquals("ICMS", taxCalculationResponse.getTaxTypeName());
        Assertions.assertEquals(1000.0, taxCalculationResponse.getBaseValue());
        Assertions.assertEquals(18.0, taxCalculationResponse.getAliquot());
        Assertions.assertEquals(180.0, taxCalculationResponse.getTaxValue());
    }

    @Test
    public void testCheckIfTaxByIdExists() {
        Long taxTypeIdInvalid = 8L;

        taxCalculationRequest.setTaxTypeId(taxTypeIdInvalid);
        taxCalculationRequest.setBaseValue(1000.0);

        Mockito.when(taxCalculationRepository.findById(taxTypeIdInvalid)).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = Assertions.
                assertThrows(EntityNotFoundException.class, () -> taxCalculationService.taxCalculation(taxCalculationRequest));

        Assertions.assertEquals("Imposto com o ID " + taxTypeIdInvalid + " não encontrado.", entityNotFoundException.getMessage());
    }

    @Test
    public void testTaxCalculationBaseWithZeroBaseValue() {
        taxModel.setId(1L);
        taxModel.setName("ICMS");
        taxModel.setAliquot(18.0);

        Mockito.when(taxCalculationRepository.findById(1L)).thenReturn(taxModel);

        taxCalculationRequest.setTaxTypeId(1L);
        taxCalculationRequest.setBaseValue(0.0);

        TaxCalculationResponseDto taxCalculationResponse = taxCalculationService.taxCalculation(taxCalculationRequest);

        Assertions.assertEquals(0.0, taxCalculationResponse.getTaxValue());
    }
}