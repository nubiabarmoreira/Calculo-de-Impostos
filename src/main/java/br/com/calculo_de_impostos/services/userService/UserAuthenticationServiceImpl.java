package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationRequestDto;
import br.com.calculo_de_impostos.infra.security.JwtTokenProvider;
import br.com.calculo_de_impostos.repositories.userRepository.UserAuthenticationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private final UserAuthenticationRepository userAuthenticationRepository;
    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAuthenticationRepository, PasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userAuthenticationRepository = userAuthenticationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String loginAndGenerateToken(UserAuthenticationRequestDto userAuthenticationRequest) {
        checkIfUserByNameExistsAndIfPasswordIsCorrect(userAuthenticationRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthenticationRequest.getUsername(),
                userAuthenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(userAuthenticationRequest.getUsername());
    }

    private String checkIfUserByNameExistsAndIfPasswordIsCorrect(UserAuthenticationRequestDto userAuthenticationRequest) {
        if (userAuthenticationRepository.findByUsername(userAuthenticationRequest.getUsername()).isPresent()) {
            if (userAuthenticationRepository.findPassword(bCryptPasswordEncoder.encode(userAuthenticationRequest.getPassword())).isPresent()) {
                return ("Usuário logado.");
            } return ("A senha está incorreta.");
        } throw new EntityNotFoundException("Usuário " + userAuthenticationRequest.getUsername() + " não localizado. Faça seu cadastro.");
    }
}