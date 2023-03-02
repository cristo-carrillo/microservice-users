package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.infrastructure.datarol.TypeRol;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;
    @RolesAllowed("ROLE_Administrador")
    @Operation(summary = "Add a new user owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request (e.g., The email format is invalid," +
                    " The value is empty, The length value is invalid, The number format is invalid)"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content),
            @ApiResponse(responseCode = "409", description = "Is already exists", content = @Content)
    })
    @PostMapping("/admin/save_owner")
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto, TypeRol.ADMINISTRATOR.getNameRol());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Add a new user client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request (e.g., The email format is invalid," +
                    " The value is empty, The length value is invalid, The number format is invalid)"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content),
            @ApiResponse(responseCode = "409", description = "Is already exists", content = @Content)
    })
    @PostMapping("/client/save_client")
    public ResponseEntity<Void> saveUserClient(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto, TypeRol.CUSTOMER.getNameRol());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RolesAllowed("ROLE_Propietario")
    @Operation(summary = "Add a new user employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request (e.g., The email format is invalid," +
                    " The value is empty, The length value is invalid, The number format is invalid)"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content),
            @ApiResponse(responseCode = "409", description = "Is already exists", content = @Content)
    })
    @PostMapping("/owner/save_employee")
    public ResponseEntity<Long> saveUserEmployee(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userHandler.saveUser(userRequestDto, TypeRol.OWNER.getNameRol()).getId(),
                HttpStatus.CREATED);
    }
    @Operation(summary = "Get user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained user by Id", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getUser(id));
    }
    @Operation(summary = "Get userRol by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validate User and Rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @GetMapping("/userRol/{id}")
    public ResponseEntity<Void> validateRolUser(@PathVariable Long id) {

        userHandler.validateRolUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Validate Rol Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validate User and Rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @RolesAllowed("ROLE_Propietario")
    @GetMapping("/owner/{id}/{username}")
    ResponseEntity<Void> validateOwner(@PathVariable Long id, @PathVariable String username ){
        userHandler.validateOwner(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Obtain id of user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validate User and Rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @GetMapping("/getId/{username}")
    ResponseEntity<Long> getIdUser(@PathVariable String username){
        return new ResponseEntity<>(userHandler.getIdUser(username), HttpStatus.OK);
    }
}
