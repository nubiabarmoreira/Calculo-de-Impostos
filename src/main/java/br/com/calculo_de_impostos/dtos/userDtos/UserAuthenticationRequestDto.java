package br.com.calculo_de_impostos.dtos.userDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserAuthenticationRequestDto {
    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Size(min = 2, message = "O nome do usuário deve ter no mínimo 2 caracteres.")
    @Column(name = "nome do usuário", unique = true)
    private String username;

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    @Column(name = "senha do usuário")
    private String password;

    public UserAuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthenticationRequestDto() {}

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
}