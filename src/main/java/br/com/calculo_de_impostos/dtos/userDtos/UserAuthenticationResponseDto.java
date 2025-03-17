package br.com.calculo_de_impostos.dtos.userDtos;

public class UserAuthenticationResponseDto {
    private String token;

    public UserAuthenticationResponseDto(String token) {
        this.token = token;
    }

    public UserAuthenticationResponseDto() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}