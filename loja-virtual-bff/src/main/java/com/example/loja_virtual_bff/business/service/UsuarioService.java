package com.example.loja_virtual_bff.business.service;

import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import com.example.loja_virtual_bff.infrastructure.clients.usuariosclient.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioClient.gravaDadosUsuario(usuarioRequestDTO);
    }

    public UsuarioResponseDTO buscaUsuarioPorEmail(String email) {
        return usuarioClient.buscaUsuarioPorEmail(email);
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioClient.deletaDadosUsuario(email);
    }
}
