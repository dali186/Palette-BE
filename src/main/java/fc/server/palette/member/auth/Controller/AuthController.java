package fc.server.palette.member.auth.Controller;

import fc.server.palette.member.auth.CustomUserDetailService;
import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.member.auth.dto.LoginDto;
import fc.server.palette.member.auth.dto.TokenDto;
import fc.server.palette.member.auth.jwt.AuthoritiesProvider;
import fc.server.palette.member.auth.jwt.JwtFilter;
import fc.server.palette.member.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final CustomUserDetailService customUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginDto loginDto) {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginDto.getEmail());

        isSamePwd(userDetails.getPassword(),loginDto.getPassword());

        UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                AuthoritiesProvider.getAuthorityCollection()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String jwt = tokenProvider.createToken(authenticationToken);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }


    private void isSamePwd(String password, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, password)) {
            throw new IllegalArgumentException("이메일 혹은 패스워드가 일치하지 않습니다.");
        }
    }




}
