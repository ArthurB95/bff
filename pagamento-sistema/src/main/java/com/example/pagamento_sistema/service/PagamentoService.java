package com.example.pagamento_sistema.service;

import com.example.pagamento_sistema.controller.request.CartaoRequestDTO;
import com.example.pagamento_sistema.infrastructure.client.PagamentoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoClient pagamentoClient;

    public Boolean verificaPagamento(CartaoRequestDTO cartaoRequestDTO) {
        return pagamentoClient.verificarCartao(cartaoRequestDTO);
    }
}
