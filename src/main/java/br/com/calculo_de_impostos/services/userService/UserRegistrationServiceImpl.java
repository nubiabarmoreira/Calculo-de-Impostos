package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationResponseDto;
import br.com.calculo_de_impostos.models.RoleModel;
import br.com.calculo_de_impostos.models.UserModel;
import br.com.calculo_de_impostos.repositories.userRepository.RoleRepository;
import br.com.calculo_de_impostos.repositories.userRepository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private final UserRegistrationRepository userRegistrationRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository, PasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequest) {
        checkIfUserByNameExists(userRegistrationRequest.getUsername());

        UserModel userModel = new UserModel();
        userModel.setUsername(userRegistrationRequest.getUsername());
        userModel.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));

        Set<RoleModel> roleModel = userRegistrationRequest.getRole()
                .stream()
                .map(role -> new RoleModel(role.name()))
                .collect(Collectors.toSet());
        roleRepository.saveAll(roleModel);

        userModel.setRole(roleModel.toString());
        userRegistrationRepository.save(userModel);

        return new UserRegistrationResponseDto(userModel.getId(), userModel.getUsername(), roleModel.toString());
    }

    private void checkIfUserByNameExists(String username) {
        if (userRegistrationRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Usuário " + username + " já existente.");
        }
    }
}