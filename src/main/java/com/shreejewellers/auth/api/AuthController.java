package com.shreejewellers.auth.api;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.shreejewellers.auth.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder encoder;
    private final UserRepository users;

    public AuthController(AuthenticationManager authenticationManager, JwtEncoder encoder, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.users = users;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = users.findByUsername(auth.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        var now = Instant.now();
        var expires = now.plus(30, ChronoUnit.MINUTES);
        var claims = JwtClaimsSet.builder().issuer("shree-jewellers-auth").subject(user.getUsername())
                .issuedAt(now).expiresAt(expires)
                .claim("roles", user.getRoles().stream().map(r -> r.getName()).sorted().toList()).build();
        var headers = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = encoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();
        return new LoginResponse(token, "Bearer", 1800);
    }

    @GetMapping("/me")
    public Map<String, Object> me(JwtAuthenticationToken auth) {
        return Map.of("username", auth.getName(), "roles", auth.getToken().getClaimAsStringList("roles"));
    }

    @GetMapping("/admin/check")
    public Map<String, String> adminCheck() {
        return Map.of("status", "ADMIN_ACCESS_GRANTED");
    }
}
