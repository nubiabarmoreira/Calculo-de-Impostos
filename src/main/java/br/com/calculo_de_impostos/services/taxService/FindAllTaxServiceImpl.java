package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.TaxResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.FindAllTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllTaxServiceImpl implements FindAllTaxService {
    @Autowired
    private final FindAllTaxRepository findAllTaxRepository;

    public FindAllTaxServiceImpl(FindAllTaxRepository findAllTaxRepository) {
        this.findAllTaxRepository = findAllTaxRepository;
    }

    @Override
    public List<TaxResponseDto> findAllTax() {
        List<TaxModel> allTax = findAllTaxRepository.findAll();
        return allTax
                .stream()
                .map(tax -> new TaxResponseDto(tax.getId(), tax.getName(), tax.getDescription(), tax.getAliquot()))
                .collect(Collectors.toList());
    }
}