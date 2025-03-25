package br.com.calculo_de_impostos.servicesTest.taxServiceTest;

import br.com.calculo_de_impostos.repositories.taxRepository.DeleteTaxByIdRepository;
import br.com.calculo_de_impostos.services.taxService.DeleteTaxByIdServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class DeleteTaxByIdServiceImplTest {

    @Mock
    private DeleteTaxByIdRepository deleteTaxByIdRepository;

    @InjectMocks
    private DeleteTaxByIdServiceImpl deleteTaxByIdService;

    @Test
    public void testDeleteTaxByIdSuccess() {
        long id = 1L;

        Mockito.when(deleteTaxByIdRepository.existsById(id)).thenReturn(true);

        deleteTaxByIdService.deleteTaxById(id);

        Mockito.verify(deleteTaxByIdRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteTaxByIdFail() {
        long invalidId = 2L;

        Mockito.when(deleteTaxByIdRepository.existsById(invalidId)).thenReturn(false);

        EntityNotFoundException entityNotFoundException = Assertions
                .assertThrows(EntityNotFoundException.class, () -> deleteTaxByIdService.deleteTaxById(invalidId));

        Assertions.assertEquals("Imposto com o ID " + invalidId + " não encontrado.", entityNotFoundException.getMessage());

        Mockito.verify(deleteTaxByIdRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }
}