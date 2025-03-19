package br.com.calculo_de_impostos.dtos.taxDtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TaxResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "\\d+", message = "O campo deve conter apenas caracteres numéricos.")
    private long id;

    @NotBlank(message = "O nome do imposto deve ser informado.")
    @Size(min = 2, message = "O nome do imposto deve ter no mínimo 2 caracteres.")
    @Column(name = "nome", unique = true)
    private String name;

    @NotBlank(message = "A descrição do imposto deve ser informada.")
    @Size(min = 2, message = "A descrição do imposto deve ter no mínimo 2 caracteres.")
    @Column(name = "descricao")
    private String description;

    @Positive(message = "A alíquota do imposto deve ser maior do que zero.")
    @Column(name = "aliquota")
    private double aliquot;

    public TaxResponseDto(long id, String name, String description, double aliquot) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.aliquot = aliquot;
    }

    public TaxResponseDto(String name, String description, double aliquot) {
        this.name = name;
        this.description = description;
        this.aliquot = aliquot;
    }

    public TaxResponseDto() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAliquot() {
        return aliquot;
    }

    public void setAliquot(double aliquot) {
        this.aliquot = aliquot;
    }
}