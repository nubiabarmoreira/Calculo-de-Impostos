package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.FindTaxByIdRepository;
import br.com.calculo_de_impostos.services.taxService.FindTaxByIdServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class FindTaxByIdServiceImplTest {

    @Mock
    private FindTaxByIdRepository findTaxByIdRepository;

    @InjectMocks
    private FindTaxByIdServiceImpl findTaxByIdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    TaxModel taxModel = new TaxModel();

//    @Test
//    public void testFindTaxByIdSuccess() {
//        long id = 3L;
//
//        TaxModel taxModel = new TaxModel();
//        taxModel.setId(id);
//        taxModel.setName("IPI");
//        taxModel.setDescription("IPI");
//        taxModel.setAliquot(3.25);
//
//        Mockito.when(findTaxByIdRepository.findById(id)).thenReturn(Optional.of(taxModel));
//
//        TaxResponseDto taxResponse = findTaxByIdService.findTaxById(id);
//
//        Assertions.assertNotNull(taxResponse);
//        Assertions.assertEquals(id, taxResponse.getId());
//        Assertions.assertEquals("IPI", taxResponse.getName());
//        Assertions.assertEquals("IPI", taxResponse.getDescription());
//        Assertions.assertEquals(3.25, taxResponse.getAliquot());
//
//        Mockito.verify(findTaxByIdRepository, Mockito.times(1)).findById(id);
//    }

    @Test
    public void testFindTaxByIdFail() {
        Long id = 1L;

        Mockito.when(findTaxByIdRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = Assertions
                .assertThrows(EntityNotFoundException.class, () -> findTaxByIdService.findTaxById(id));

        Assertions.assertEquals("O imposto com o ID " + id + " não foi encontrado.", entityNotFoundException.getMessage());
    }
}