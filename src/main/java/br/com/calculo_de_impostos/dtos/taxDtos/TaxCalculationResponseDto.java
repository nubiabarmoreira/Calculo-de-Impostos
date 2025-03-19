package br.com.calculo_de_impostos.dtos.taxDtos;

import br.com.calculo_de_impostos.models.TaxModel;

public class TaxCalculationResponseDto {
    private String taxTypeName;
    private double baseValue;
    private double aliquot;
    private double taxValue;

    public TaxCalculationResponseDto(String taxTypeName, double baseValue, double aliquot, double taxValue) {
        this.taxTypeName = taxTypeName;
        this.baseValue = baseValue;
        this.aliquot = aliquot;
        this.taxValue = taxValue;
    }

    public TaxCalculationResponseDto() {}

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double getAliquot() {
        return aliquot;
    }

    public void setAliquot(double aliquot) {
        this.aliquot = aliquot;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
    }
}