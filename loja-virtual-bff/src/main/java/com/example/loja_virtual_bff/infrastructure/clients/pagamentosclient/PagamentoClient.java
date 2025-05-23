package com.example.loja_virtual_bff.infrastructure.clients.pagamentosclient;

import com.example.loja_virtual_bff.api.request.CartaoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pagamento-api", url = "localhost:8383")
public interface PagamentoClient {

    @PostMapping("/pagamento")
    Boolean verificaPagamento(@RequestBody CartaoRequestDTO cartaoRequestDTO);

}
