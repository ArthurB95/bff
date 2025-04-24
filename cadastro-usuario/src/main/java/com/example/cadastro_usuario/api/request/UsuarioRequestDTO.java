package com.example.cadastro_usuario.api.request;

import com.example.cadastro_usuario.entities.EnderecoEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private String nome;

    private String email;

    private String documento;

    private EnderecoRequestDTO endereco;
}
