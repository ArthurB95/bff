package com.example.cadastro_usuario.repository;

import com.example.cadastro_usuario.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
