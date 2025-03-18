package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationRequestDto;
import jakarta.validation.Valid;

public interface UserAuthenticationService {
    String loginAndGenerateToken(@Valid UserAuthenticationRequestDto userAuthenticationRequest);
}