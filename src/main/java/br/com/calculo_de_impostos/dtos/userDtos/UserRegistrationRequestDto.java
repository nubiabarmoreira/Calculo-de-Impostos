package br.com.calculo_de_impostos.dtos.userDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserRegistrationRequestDto {
    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Size(min = 2, message = "O nome do usuário deve ter no mínimo 2 caracteres.")
    @Column(name = "nome do usuário", unique = true)
    private String username;

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    @Column(name = "senha do usuário")
    private String password;          //deve ser armazenada de forma segura (hashing com BCrypt)

    @NotBlank(message = "Informe se o cadastro será de usuário ou de administrador.")
    @Column(name = "papel/função")
    private RoleEnum role;

    public UserRegistrationRequestDto(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserRegistrationRequestDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}