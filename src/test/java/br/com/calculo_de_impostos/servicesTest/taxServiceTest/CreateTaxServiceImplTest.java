package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.SaveTaxRepository;
import br.com.calculo_de_impostos.services.taxService.CreateTaxServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class CreateTaxServiceImplTest {

    @Mock
    private SaveTaxRepository saveTaxRepository;

    @InjectMocks
    private CreateTaxServiceImpl createTaxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateTaxSuccess() {
        String name = "ICMS";
        String description = "ICMS";
        double aliquot = 18.0;

        TaxModel taxCreated = new TaxModel(name, description, aliquot);
        taxCreated.setId(1L);

        when(saveTaxRepository.save(any(TaxModel.class))).thenReturn(taxCreated);

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

        IllegalArgumentException exception = Assertions.
                assertThrows(IllegalArgumentException.class, () -> createTaxService.createTax(name, description, aliquot));

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

        TaxModel taxToCreate = new TaxModel(name, description, aliquot);
        TaxModel taxCreated = new TaxModel(name, description, aliquot);

        when(saveTaxRepository.save(taxToCreate)).thenReturn(taxCreated);

        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, () -> createTaxService.createTax(name, description, aliquot));

        Assertions.assertEquals("A alíquota do imposto deve ser maior do que zero.", exception.getMessage());
    }
}