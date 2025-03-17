//package br.com.calculo_de_impostos.services.userService;
//
//import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationRequestDto;
//import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationResponseDto;
//import br.com.calculo_de_impostos.models.TaxModel;
//import br.com.calculo_de_impostos.models.UserModel;
//import br.com.calculo_de_impostos.repositories.userRepository.UserAuthenticationRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserAuthenticationServiceImpl implements UserAuthenticationService {
//    @Autowired
//    private final UserAuthenticationRepository userAuthenticationRepository;
//
//    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAuthenticationRepository) {
//        this.userAuthenticationRepository = userAuthenticationRepository;
//    }
//
////    @Override
////    public UserAuthenticationResponseDto authenticateUser(UserAuthenticationRequestDto userAuthenticationRequest) {
////        checkIfUserByNameExists(userAuthenticationRequest.getUsername());
//////        checkIfPasswordIsCorrect(userAuthenticationRequest.getPassword());
////
//////        UserModel userToAuthenticate = userAuthenticationRepository.
//////        return ;
////    }
////
////    private UserModel checkIfUserByNameExists(String username) {
////        return userAuthenticationRepository.findByUsername(username)
////                .orElseThrow(() -> new EntityNotFoundException("Usuário " + username + " não localizado. Faça seu cadastro."));
////    }
//
////    private void checkIfPasswordIsCorrect(String password) {
////
////    }
//}