package br.com.calculo_de_impostos.services.userService;

import br.com.calculo_de_impostos.models.UserModel;
import br.com.calculo_de_impostos.repositories.userRepository.UserAuthenticationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public CustomUserDetailsService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userAuthenticationRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário " + username + " não localizado."));

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(userModel.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                userModel.getUsername(),
                userModel.getPassword(),
                authorities
        );
    }
}