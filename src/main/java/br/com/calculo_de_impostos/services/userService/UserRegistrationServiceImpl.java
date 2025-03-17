package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationResponseDto;
import br.com.calculo_de_impostos.models.UserModel;
import br.com.calculo_de_impostos.repositories.userRepository.UserRegistrationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private final UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequest) {
        checkIfUserByNameExists(userRegistrationRequest.getUsername());

        UserModel userToRegister = new UserModel(
                userRegistrationRequest.getUsername(),
                userRegistrationRequest.getPassword(),
                userRegistrationRequest.getRole());
        UserModel userRegistered = userRegistrationRepository.save(userToRegister);

        return new UserRegistrationResponseDto(
                userRegistered.getId(),
                userRegistered.getUsername(),
                userRegistered.getRole());
    }

    private void checkIfUserByNameExists(String username) {
        if (userRegistrationRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Usuário " + username + " já existente.");
        }
    }
}