package br.com.calculo_de_impostos.dtos.userDtos;

public class RegisterUsersResponseDto {
    private long id;
    private String usarname;
    private String role;

    public RegisterUsersResponseDto(long id, String usarname, String role) {
        this.id = id;
        this.usarname = usarname;
        this.role = role;
    }

    public RegisterUsersResponseDto(String usarname, String role) {
        this.usarname = usarname;
        this.role = role;
    }

    public RegisterUsersResponseDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsarname() {
        return usarname;
    }

    public void setUsarname(String usarname) {
        this.usarname = usarname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}