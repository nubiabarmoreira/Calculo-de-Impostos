package br.com.calculo_de_impostos.dtos;

import br.com.calculo_de_impostos.models.TaxModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class TaxCalculationRequestDto {
    public final TaxModel taxModel = new TaxModel();

    @NotNull
    @PositiveOrZero(message = "O ID do imposto deve ser um número positivo.")
    @Pattern(regexp = "\\d+", message = "O ID do imposto deve conter apenas caracteres numéricos.")
    @Column(name = "ID do tipo de imposto")
    private Long taxTypeId;

    @NotNull
    @PositiveOrZero(message = "O valor base do imposto deve ser um número positivo.")
    @Pattern(regexp = "\\d+", message = "O valor base do imposto deve conter apenas caracteres numéricos.")
    @Column(name = "valor base do imposto")
    private double baseValue;

    public TaxCalculationRequestDto(Long taxTypeId, double baseValue) {
        this.taxTypeId = taxTypeId;
        this.baseValue = baseValue;
    }

    public TaxCalculationRequestDto() {}

    public Long getTaxTypeId() {
        return taxModel.getId();
    }

    public void setTaxTypeId(Long taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }
}