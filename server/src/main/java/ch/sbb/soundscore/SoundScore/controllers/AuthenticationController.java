package ch.sbb.soundscore.SoundScore.controllers;


import ch.sbb.soundscore.SoundScore.dtos.LoginResponse;
import ch.sbb.soundscore.SoundScore.dtos.LoginUserDto;
import ch.sbb.soundscore.SoundScore.dtos.RegisterArtistDto;
import ch.sbb.soundscore.SoundScore.dtos.RegisterUserDto;
import ch.sbb.soundscore.SoundScore.entities.Artist;
import ch.sbb.soundscore.SoundScore.entities.User;
import ch.sbb.soundscore.SoundScore.services.AuthenticationService;
import ch.sbb.soundscore.SoundScore.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup/artist")
    public ResponseEntity<Artist> registerArtist(@RequestBody RegisterArtistDto registerUserDto) {
        Artist registeredUser = authenticationService.signupArtist(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }


}

