package br.com.calculo_de_impostos.repositories.userRepository;

import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserModel, Long> {
    UserModel save(UserRegistrationRequestDto userRegistrationRequest);
    Optional<UserModel> findByUsername(String username);
}