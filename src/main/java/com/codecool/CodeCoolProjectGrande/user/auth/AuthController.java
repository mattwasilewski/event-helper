package com.codecool.CodeCoolProjectGrande.user.auth;

import com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3.ReCAPTCHAv3Exception;
import com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3.ReCAPTCHAv3Response;
import com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3.ReCAPTCHAv3Utils;
import com.codecool.CodeCoolProjectGrande.user.jwt.JwtUtils;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final static double SCORES_LEVEL = 0.7;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseCookie> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String token = loginRequest.getToken();
        String address = request.getRemoteAddr();
        try {
            ReCAPTCHAv3Response response = ReCAPTCHAv3Utils.request(token, address);
            if (response.isSuccess()) {
                if (response.getScore() > SCORES_LEVEL) {
                    ResponseCookie jwtCookie = userService.authenticateUser(loginRequest);
                    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                            .body(jwtCookie);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ReCAPTCHAv3Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }


}

