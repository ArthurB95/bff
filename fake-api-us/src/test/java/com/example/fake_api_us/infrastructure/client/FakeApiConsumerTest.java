package com.example.fake_api_us.infrastructure.client;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.business.service.FakeApiService;
import com.example.fake_api_us.business.service.ProdutoService;
import com.example.fake_api_us.infrastructure.exception.BusinessException;
import com.example.fake_api_us.infrastructure.message.consumer.FakeApiConsumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FakeApiConsumerTest {

    @InjectMocks
    FakeApiConsumer consumer;

    @Mock
    ProdutoService service;

    @Test
    @DisplayName("Deve receber mensagem ProdutoDTO com sucesso")
    void deve_receber_mensagem_ProdutoDTO_com_sucesso() {
        ProductsDTO produtoDTO = ProductsDTO.builder().descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();

        doNothing().when(service).salvaProdutosConsumer(produtoDTO);

        consumer.consumerProducerProdutos(produtoDTO);

        verify(service).salvaProdutosConsumer(produtoDTO);
        verifyNoMoreInteractions(service);
    }
    @Test
    @DisplayName("Deve gerar exception caso erro no consumer")
    void deve_gerar_exception_caso_erro_no_consumer() {
        ProductsDTO produtoDTO = ProductsDTO.builder().descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();

        doThrow(new RuntimeException("Erro ao consumir mensagem")).when(service).salvaProdutosConsumer(produtoDTO);

        BusinessException e = assertThrows(BusinessException.class, () -> consumer.consumerProducerProdutos(produtoDTO));

        assertThat(e.getMessage(), is("Erro ao consumir mensagem do kafka "));
        verify(service).salvaProdutosConsumer(produtoDTO);
        verifyNoMoreInteractions(service);
    }
}
