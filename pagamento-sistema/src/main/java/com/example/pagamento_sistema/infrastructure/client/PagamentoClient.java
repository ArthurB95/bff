package com.example.pagamento_sistema.infrastructure.client;

import com.example.pagamento_sistema.controller.request.CartaoRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PagamentoClient {

    public Boolean verificarCartao(CartaoRequestDTO cartaoRequestDTO) {

        if(cartaoRequestDTO.getNumeroCartao().endsWith("8080")) {
            return true;
        }

        return false;
    }
}
