package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.handler.IRolHandler;
import com.pragma.powerup.application.mapper.rol.IRolRequestMapper;
import com.pragma.powerup.application.mapper.rol.IRolResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.RolModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RolHandler implements IRolHandler {

    private final IRolServicePort rolServicePort;
    private final IRolRequestMapper rolRequestMapper;
    private final IRolResponseMapper rolResponseMapper;
    @Override
    public void saveRol(RolRequestDto rolRequestDto) {
        RolModel rolModel = rolRequestMapper.toRol(rolRequestDto);
        rolServicePort.saveRol(rolModel);
    }

    @Override
    public List<RolResponseDto> getAllRols() {
        return rolResponseMapper.toResponseList(rolServicePort.getAllRols());
    }

    @Override
    public RolResponseDto getRol(Long id) {
        return rolResponseMapper.toResponse(rolServicePort.getRol(id));
    }
}
