package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.handler.IRolHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rol")
@RequiredArgsConstructor
public class RolRestController {

    private final IRolHandler rolHandler;
    @Operation(summary = "Add a new rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol created", content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content),
            @ApiResponse(responseCode = "409", description = "Rol already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveRol(@RequestBody RolRequestDto rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained all roles", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RolResponseDto>> getAllRolls(){
        return ResponseEntity.ok(rolHandler.getAllRols());
    }
    @Operation(summary = "Get Rol by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained rol by Id", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found for the requested petition"
                    , content = @Content),
            @ApiResponse(responseCode = "401", description = "Permission denied", content = @Content)
    })
    @RolesAllowed("ROLE_Propietario")
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> getRol(@PathVariable Long id){
        return ResponseEntity.ok(rolHandler.getRol(id));
    }

}
