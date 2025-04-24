package com.example.cadastro_usuario.service;

import com.example.cadastro_usuario.api.converter.UsuarioConverter;
import com.example.cadastro_usuario.api.request.UsuarioRequestDTO;
import com.example.cadastro_usuario.api.response.UsuarioResponseDTO;
import com.example.cadastro_usuario.entities.UsuarioEntity;
import com.example.cadastro_usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity) {
       return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            UsuarioEntity usuarioEntity = salvarUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            return usuarioConverter.paraUsuarioResponseDTO(usuarioEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar dados de usuário");
        }
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
        return usuarioConverter.paraUsuarioResponseDTO(usuarioRepository.findByEmail(email));
    }

    public void deletarDadosUsuario(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
