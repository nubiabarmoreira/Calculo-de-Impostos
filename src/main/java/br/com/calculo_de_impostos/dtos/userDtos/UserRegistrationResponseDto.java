package br.com.calculo_de_impostos.dtos.userDtos;

import java.util.Set;

public class UserRegistrationResponseDto {
    private long id;
    private String username;
    private Set<RoleEnum> role;

    public UserRegistrationResponseDto(long id, String username, Set<RoleEnum> role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserRegistrationResponseDto(String username, Set<RoleEnum> role) {
        this.username = username;
        this.role = role;
    }

    public UserRegistrationResponseDto() {}

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

    public Set<RoleEnum> getRole() {
        return role;
    }

    public void setRole(Set<RoleEnum> role) {
        this.role = role;
    }
}