package br.com.calculo_de_impostos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "impostos")
public class TaxModel {
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome do imposto deve ser informado.")
    @Size(min = 2, message = "O nome do imposto deve ter no mínimo 2 caracteres.")
    @Column(name = "nome do imposto")
    private String name;

    @NotBlank(message = "A descrição do imposto deve ser informada.")
    @Size(min = 2, message = "A descrição do imposto deve ter no mínimo 2 caracteres.")
    @Column(name = "descrição do imposto")
    private String description;

    @Positive(message = "A alíquota do imposto deve ser maior do que zero.")
    @Column(name = "alíquota")
    private double aliquot;

    public TaxModel(String name, String description, double aliquot) {
        this.name = name;
        this.description = description;
        this.aliquot = aliquot;
    }

    public TaxModel() {}

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