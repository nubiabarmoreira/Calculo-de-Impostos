package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.repositories.taxRepository.DeleteTaxByIdRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaxByIdServiceImpl implements DeleteTaxByIdService {
    @Autowired
    private final DeleteTaxByIdRepository deleteTaxByIdRepository;

    public DeleteTaxByIdServiceImpl(DeleteTaxByIdRepository deleteTaxByIdRepository){
        this.deleteTaxByIdRepository = deleteTaxByIdRepository;
    }

    @Override
    public void deleteTaxById(long id) {
        if (!deleteTaxByIdRepository.existsById(id)) {
            throw new EntityNotFoundException("O imposto com o ID " + id + " não foi encontrado.");
        }
        deleteTaxByIdRepository.deleteById(id);
    }
}