package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.dtos.userDtos.RoleEnum;
import br.com.calculo_de_impostos.dtos.userDtos.UserAuthenticationRequestDto;
import br.com.calculo_de_impostos.dtos.userDtos.UserRegistrationRequestDto;
import br.com.calculo_de_impostos.infra.security.JwtUtil;
import br.com.calculo_de_impostos.models.UserModel;
import br.com.calculo_de_impostos.repositories.userRepository.UserAuthenticationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    private final UserAuthenticationRepository userAuthenticationRepository;
    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRegistrationRequestDto userRegistrationRequest;

    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAuthenticationRepository, PasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRegistrationRequestDto userRegistrationRequest) {
        this.userAuthenticationRepository = userAuthenticationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRegistrationRequest = userRegistrationRequest;
    }

    @Override
    public String loginAndGenerateToken(UserAuthenticationRequestDto userAuthenticationRequest) {
        checkIfUserByNameExistsAndIfPasswordIsCorrect(userAuthenticationRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthenticationRequest.getUsername(),
                userAuthenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = Collections.singletonList(userRegistrationRequest.getRole().name());
        System.out.println("Role do usuário " + userAuthenticationRequest.getUsername() + ": " + roles);

        return jwtUtil.generateToken(userAuthenticationRequest.getUsername(), roles);
    }

    private String checkIfUserByNameExistsAndIfPasswordIsCorrect(UserAuthenticationRequestDto userAuthenticationRequest) {
        if (userAuthenticationRepository.findByUsername(userAuthenticationRequest.getUsername()).isPresent()) {
            if (userAuthenticationRepository.findByPassword(bCryptPasswordEncoder.encode(userAuthenticationRequest.getPassword())).isPresent()) {
                return ("Usuário logado.");
            } return ("A senha está incorreta.");
        } throw new EntityNotFoundException("Usuário " + userAuthenticationRequest.getUsername() + " não localizado. Faça seu cadastro.");
    }

    public UserDetails loadUserByUsername(UserAuthenticationRequestDto userAuthenticationRequest) throws UsernameNotFoundException {
        userAuthenticationRepository.findByUsername(userAuthenticationRequest.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Usuário " + userAuthenticationRequest.getUsername() + " não localizado."));

        return new org.springframework.security.core.userdetails.User(userAuthenticationRequest.getUsername(), userAuthenticationRequest.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userRegistrationRequest.getRole().name())));
    }
}