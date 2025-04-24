package com.example.loja_virtual_bff.infrastructure.clients.usuariosclient;

import com.example.loja_virtual_bff.api.request.UsuarioRequestDTO;
import com.example.loja_virtual_bff.api.response.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "usuario-api", url = "localhost:8282")
public interface UsuarioClient {

    @PostMapping("/user")
    UsuarioResponseDTO gravaDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO);

    @GetMapping("/user")
    UsuarioResponseDTO buscaUsuarioPorEmail(@RequestParam("email") String email);

    @DeleteMapping("/user")
    Void deletaDadosUsuario(@RequestParam("email") String email);
}
