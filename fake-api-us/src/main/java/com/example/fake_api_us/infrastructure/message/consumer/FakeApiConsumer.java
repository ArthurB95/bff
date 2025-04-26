package com.example.fake_api_us.infrastructure.message.consumer;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.business.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FakeApiConsumer {

    private final ProdutoService produtoService;

    @KafkaListener(topics = "${topico.fake-api.consumer.nome}", groupId = "${topico.fake-api.consumer.group-id}")
    public void consumerProducerProdutos(ProductsDTO productsDTO) {
        try {
            produtoService.salvaProdutosConsumer(productsDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consumir mensagem do kafka " + e);
        }
    }
}
