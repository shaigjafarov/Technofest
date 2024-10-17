package az.technofest.service;

import az.technofest.configuration.security.JwtProvider;
import az.technofest.dao.entity.User;
import az.technofest.dao.repository.UserRepository;
import az.technofest.model.dto.UserPrincipal;
import az.technofest.model.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;



    public String loginReturnJwt(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return getLoginResponse(userPrincipal);
    }

    private String getLoginResponse(UserPrincipal userPrincipal) {

        String accessToken = jwtProvider.generateToken(userPrincipal, 300000L);
        System.out.println(accessToken);
        return accessToken;
    }

    public void createUser(AuthRequest authRequest) {
        User user = User.builder()
                .username(authRequest.getUsername())
                .password( new BCryptPasswordEncoder().encode(authRequest.getPassword()))
                .roles("ADMIN")
                .build();

        userRepository.save(user);
    }
}
