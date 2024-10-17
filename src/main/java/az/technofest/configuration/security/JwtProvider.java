package az.technofest.configuration.security;

import az.technofest.dao.entity.User;
import az.technofest.model.dto.UserPrincipal;
import az.technofest.model.request.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {



    private static final String JWT_HEADER_STRING = "Authorization";
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private final CustomUserDetailsService userDetailsService;

    private  String publicKey;
    private  String privateKey;


    public JwtProvider(CustomUserDetailsService userDetailsService) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // RSA key size
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Base64 formatına çevirme
         publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
         privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        // Çıktıları yazdırma
        System.out.println("Public Key (Base64): " + publicKey);
        System.out.println("Private Key (Base64): " + privateKey);

        this.userDetailsService=userDetailsService;
    }


    private PrivateKey preparePrivateKey() {
        try {
//            String privateKeyPEM = publicKey.replace("-----BEGIN PRIVATE KEY-----", "")
//                    .replace("-----END PRIVATE KEY-----", "")
//                    .replaceAll("\\s", "");
            KeyFactory kf = getKeyFactory();

            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey preparePublicKey() {
        try {
//            String publicKeyPEM = publicKey.replace("-----BEGIN PUBLIC KEY-----", "")
//                    .replace("-----END PUBLIC KEY-----", "")
//                    .replaceAll("\\s", "");

            KeyFactory kf = getKeyFactory();
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return kf.generatePublic(keySpecX509);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateToken(UserPrincipal userPrincipal, Long expireDate) {
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .signWith(preparePrivateKey(), SignatureAlgorithm.RS512)
                .compact();
    }

    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(preparePublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER_STRING);
        if (bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String bearerToken = resolveToken( request);
        if (bearerToken == null) {
            return null;
        }
        Claims claims = read(bearerToken);
        String username = claims.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return username != null ?
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
    }

    public boolean isTokenValid(HttpServletRequest request) {
        String bearerToken = resolveToken(request);
        if (bearerToken == null) {
            return false;
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(preparePublicKey())
                .build()
                .parseClaimsJws(bearerToken)
                .getBody();

        return !claims.getExpiration().before(new Date());
    }

    private KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unknown key generation algorithm", e);
        }
    }







}
