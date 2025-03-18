package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.RoleEnum;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationResponseDto;
import br.com.calculo_de_impostos.models.UserModel;
import br.com.calculo_de_impostos.repositories.userRepository.UserRegistrationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private final UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final RoleEnum roleEnum;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository, PasswordEncoder bCryptPasswordEncoder, RoleEnum roleEnum) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleEnum = roleEnum;
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequest) {
        checkIfUserByNameAndRoleExists(userRegistrationRequest);

        UserModel newUser = new UserModel();
        newUser.setUsername(userRegistrationRequest.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));
        newUser.setRole(userRegistrationRequest.getRole());

        userRegistrationRepository.save(newUser);

        return new UserRegistrationResponseDto(newUser.getId(), newUser.getUsername(), newUser.getRole());
    }

    private String checkIfUserByNameAndRoleExists(UserRegistrationRequestDto userRegistrationRequest) {
        if (userRegistrationRepository.findByUsername(userRegistrationRequest.getUsername()).isPresent()) {
            if (roleEnum.equals(userRegistrationRequest.getRole())) {
                return ("Usuário registrado.");
            } throw new EntityNotFoundException("Role " + userRegistrationRequest.getRole() + " não listada.");
        } throw new EntityNotFoundException("Usuário " + userRegistrationRequest.getUsername() + " já existente. Forneça outro.");
    }
}