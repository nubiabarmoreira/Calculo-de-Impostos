package br.com.calculo_de_impostos.dtos.userDtos;

public class UserRegistrationResponseDto {
    private long id;
    private String username;
    private RoleEnum role;

    public UserRegistrationResponseDto(long id, String username, RoleEnum role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserRegistrationResponseDto(String username, RoleEnum role) {
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}