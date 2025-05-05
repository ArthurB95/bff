package com.example.fake_api_us.infrastructure.producer;

import com.example.fake_api_us.infrastructure.exception.BusinessException;
import com.example.fake_api_us.infrastructure.message.producer.FakeApiProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FakeApiProducerTest {

    @InjectMocks
    FakeApiProducer producer;

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    @DisplayName("Deve enviar resposta cadastro produto com sucesso")
    void deve_enviar_resposta_cadastro_produto_com_sucesso() {

        String mensagem = "Produto cadastro com sucesso";

        producer.enviaRespostaCadastroProdutos(mensagem);

        verify(kafkaTemplate).send(any(), messageCaptor.capture());
        assertEquals(mensagem, messageCaptor.getValue());
    }

    @Test
    @DisplayName("Deve retornar exception caso ocorra erro na producao da mensagem")
    void deve_retornar_exception_caso_ocorra_erro_na_producao_da_mensagem() {
        doThrow(new RuntimeException("Erro ao produzir mensagem")).when(kafkaTemplate).send(any(), any());

        BusinessException e = assertThrows(BusinessException.class, () -> producer.enviaRespostaCadastroProdutos(null));

        assertThat(e.getMessage(), is("Erro ao produzir mensagem do kafka "));
        verifyNoMoreInteractions(kafkaTemplate);    }
}
