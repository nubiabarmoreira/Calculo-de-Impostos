package br.com.calculo_de_impostos.repositories.userRepository;

import br.com.calculo_de_impostos.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserModel, Long> {

}