package com.example.pagamento_sistema.controller;

import com.example.pagamento_sistema.controller.request.CartaoRequestDTO;
import com.example.pagamento_sistema.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagamento")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping()
    public ResponseEntity<Boolean> verificaPagamento(@RequestBody CartaoRequestDTO cartaoRequestDTO) {
        return ResponseEntity.ok(pagamentoService.verificaPagamento(cartaoRequestDTO));
    }

}
