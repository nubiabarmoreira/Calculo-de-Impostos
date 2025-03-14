package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationResponseDto;
import jakarta.validation.Valid;

public interface UserRegistrationService {
    UserRegistrationResponseDto registerUser(@Valid UserRegistrationRequestDto userRegistrationRequest);
}