package br.com.calculo_de_impostos.dtos.userDtos;

public class UserRegistrationResponseDto {
    private long id;
    private String username;
    private String role;

    public UserRegistrationResponseDto(long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserRegistrationResponseDto(String username, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}