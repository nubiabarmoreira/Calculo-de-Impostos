package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.FindAllTaxRepository;
import br.com.calculo_de_impostos.services.taxService.FindAllTaxServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class FindAllTaxServiceImplTest {

    @Mock
    private FindAllTaxRepository findAllTaxRepository;

    @InjectMocks
    private FindAllTaxServiceImpl findAllTaxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllTaxSuccess() {
        List<TaxModel> allTax = List.of(
                new TaxModel(1L, "ICMS", "ICMS", 18.0),
                new TaxModel(2L, "IPI", "IPI", 3.25)
        );

        Mockito.when(findAllTaxRepository.findAll()).thenReturn(allTax);

        List<TaxResponseDto> allTaxResponse = findAllTaxService.findAllTax();

        Assertions.assertEquals(2, allTaxResponse.size());
        Assertions.assertEquals("ICMS", allTaxResponse.get(0).getName());
        Assertions.assertEquals("IPI", allTaxResponse.get(1).getName());
        Assertions.assertEquals(18.0, allTaxResponse.get(0).getAliquot());
        Assertions.assertEquals(3.25, allTaxResponse.get(1).getAliquot());
    }

    @Test
    public void testFindAllTaxReturnsEmptyList() {
        Mockito.when(findAllTaxRepository.findAll()).thenReturn(Collections.emptyList());

        List<TaxResponseDto> allTaxResponse = findAllTaxService.findAllTax();

        Assertions.assertTrue(allTaxResponse.isEmpty());
    }
}