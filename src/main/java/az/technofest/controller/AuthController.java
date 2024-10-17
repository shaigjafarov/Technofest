package az.technofest.controller;

import az.technofest.configuration.security.CustomUserDetailsService;
import az.technofest.configuration.security.JwtProvider;
import az.technofest.model.request.AuthRequest;
import az.technofest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {

//        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        // Burada kullanıcı adı ve şifre kontrolü yapın
        // Eğer kullanıcı doğrulanırsa, JWT oluşturun
        return authService.loginReturnJwt(authRequest);
    }


    @PostMapping("/signup")
    public void signup(@RequestBody AuthRequest authRequest) {
        // Burada kullanıcı adı ve şifre kontrolü yapın
        // Eğer kullanıcı doğrulanırsa, JWT oluşturun
        authService.createUser(authRequest);
    }


}