package az.technofest.configuration.security;

import az.technofest.dao.entity.User;
import az.technofest.dao.repository.UserRepository;
import az.technofest.model.dto.UserPrincipal;
import az.technofest.model.request.AuthRequest;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(Arrays.asList(user.getRoles().split(",")
                )));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


}