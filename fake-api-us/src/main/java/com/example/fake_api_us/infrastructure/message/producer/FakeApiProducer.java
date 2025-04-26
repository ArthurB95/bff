package com.example.fake_api_us.infrastructure.message.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FakeApiProducer {

    @Value("${topico.fake-api.producer.nome}")
    public String topico;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public FakeApiProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviaRespostaCadastroProdutos(final String mensagem) {
        try {
            kafkaTemplate.send(topico, mensagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao produzir mensagem do kafka " + e);
        }
    }


}
