//package br.com.calculo_de_impostos.controllers.userController;
//
//import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationRequestDto;
//import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationResponseDto;
//import br.com.calculo_de_impostos.services.userService.UserAuthenticationService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/usuários")
//public class UserAuthenticationController {
//    @Autowired
//    private final UserAuthenticationService userAuthenticationService;
//
//    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
//        this.userAuthenticationService = userAuthenticationService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserAuthenticationResponseDto> authenticateUser(@Valid @RequestBody UserAuthenticationRequestDto userAuthenticationRequest) {
//        UserAuthenticationResponseDto userAuthenticationResponse = userAuthenticationService.authenticateUser(userAuthenticationRequest);
//        return ResponseEntity.ok(userAuthenticationResponse);
//    }
//}