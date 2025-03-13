package br.com.calculo_de_impostos.services.taxService;

import br.com.calculo_de_impostos.dtos.TaxCalculationRequestDto;
import br.com.calculo_de_impostos.dtos.TaxCalculationResponseDto;
import br.com.calculo_de_impostos.models.TaxModel;
import br.com.calculo_de_impostos.repositories.taxRepository.TaxCalculationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculationServiceImpl implements TaxCalculationService {
    @Autowired
    private final TaxCalculationRepository taxCalculationRepository;

    public TaxCalculationServiceImpl(TaxCalculationRepository taxCalculationRepository) {
        this.taxCalculationRepository = taxCalculationRepository;
    }

    @Override
    public TaxCalculationResponseDto taxCalculation(TaxCalculationRequestDto calculationRequest) {
        TaxModel taxModel = checkIfTaxByIdExists(calculationRequest.getTaxTypeId());
        double taxValue = taxCalculationBase(calculationRequest.getBaseValue(), taxModel.getAliquot());
        return new TaxCalculationResponseDto(taxModel.getName(), calculationRequest.getBaseValue(), taxModel.getAliquot(), taxValue);
    }

    private TaxModel checkIfTaxByIdExists(Long id) {
        return taxCalculationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imposto com o ID " + id + "não encontrado."));
    }

    private double taxCalculationBase(double baseValue, double aliquot) {
        return (baseValue * aliquot) / 100;
    }
}