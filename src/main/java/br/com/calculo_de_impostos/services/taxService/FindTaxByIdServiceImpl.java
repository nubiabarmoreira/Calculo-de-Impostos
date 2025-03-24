package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.taxDtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.FindTaxByIdRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaxByIdServiceImpl implements FindTaxByIdService {
    @Autowired
    private final FindTaxByIdRepository findTaxByIdRepository;

    public FindTaxByIdServiceImpl(FindTaxByIdRepository findTaxByIdRepository) {
        this.findTaxByIdRepository = findTaxByIdRepository;
    }

    @Override
    public TaxResponseDto findTaxById(Long id) {
        TaxModel taxById = findTaxByIdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("O imposto com o ID " + id + " não foi encontrado."));

        return new TaxResponseDto(taxById.getId(), taxById.getName(), taxById.getDescription(), taxById.getAliquot());
    }
}