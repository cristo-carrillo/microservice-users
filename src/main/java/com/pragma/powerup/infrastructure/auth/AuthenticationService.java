package com.pragma.powerup.infrastructure.auth;

import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.security.JwtService;
import com.pragma.powerup.infrastructure.security.aut.DetailsUser;
import com.pragma.powerup.infrastructure.security.aut.IUserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final IUserDetailsMapper userDetailsMapper;
    private final IRolRepository rolRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse  authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = optionalDetailsUser(request.getEmail()).get();

        var jwtToken = jwtService.generateToken(user, user.getRole());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Optional<DetailsUser> optionalDetailsUser(String username) {
        Optional<UserEntity> userEntity = repository.findByEmail(username);
        if(userEntity.isEmpty()){
            throw new NoDataFoundException();
        }
        Optional<RolEntity> rolEntity = rolRepository.findById(userEntity.get().getIdRol());
        if(rolEntity.isEmpty()){
            throw new NoDataFoundException();
        }
        DetailsUser user = userDetailsMapper.toUser(userEntity.get());
        user.setRole(rolEntity.get().getName());
        return Optional.of(user);
    }


    public UserAuthDto getUserAuth(String email) {
        Optional<UserEntity> userEntity = repository.findByEmail(email);
        if(userEntity.isEmpty()){
            throw new NoDataFoundException();
        }
        Optional<RolEntity> rolEntity = rolRepository.findById(userEntity.get().getIdRol());
        if(rolEntity.isEmpty()){
            throw new NoDataFoundException();
        }
        DetailsUser user = userDetailsMapper.toUser(userEntity.get());
        user.setRole(rolEntity.get().getName());

        return userDetailsMapper.toUserAuth(user);
    }
}
