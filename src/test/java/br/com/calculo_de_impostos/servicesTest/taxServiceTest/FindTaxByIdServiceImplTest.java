package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.FindTaxByIdRepository;
import br.com.calculo_de_impostos.services.taxService.FindTaxByIdService;
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
public class FindTaxByIdServiceImplTest {

    @Mock
    private FindTaxByIdRepository findTaxByIdRepository;

    @InjectMocks
    private FindTaxByIdService findTaxByIdService;

    TaxModel taxModel = new TaxModel();

    @Test
    public void testFindTaxByIdSuccess() {
        long id = 1L;
        taxModel.setName("IPI");
        taxModel.setDescription("IPI");
        taxModel.setAliquot(3.25);

        Mockito.when(findTaxByIdRepository.findById(id)).thenReturn(taxModel);

        TaxResponseDto taxResponse = findTaxByIdService.findTaxById(id);

        Assertions.assertEquals(id, taxResponse.getId());
        Assertions.assertEquals("IPI", taxResponse.getName());
        Assertions.assertEquals("IPI", taxResponse.getDescription());
        Assertions.assertEquals(3.25, taxResponse.getAliquot());
    }
}