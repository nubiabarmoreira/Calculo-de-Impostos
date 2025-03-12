package br.com.calculo_de_impostos.services.taxService;

import jakarta.validation.Valid;

public interface DeleteTaxByIdService {
    void deleteTaxById(@Valid long id);
}