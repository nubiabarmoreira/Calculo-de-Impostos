package br.com.calculo_de_impostos.models;

import br.com.calculo_de_impostos.dtos.userDtos.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Size(min = 2, message = "O nome do usuário deve ter no mínimo 2 caracteres.")
    @Column(name = "nome", unique = true)
    private String username;

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    @Column(name = "senha")
    private String password;          //deve ser armazenada de forma segura (hashing com BCrypt)

    @NotBlank(message = "Informe se o cadastro será de usuário ou de administrador.")
    @Column(name = "autorizacao")
    private RoleEnum role;

    public UserModel(long id, String username, String password, RoleEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserModel(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserModel() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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