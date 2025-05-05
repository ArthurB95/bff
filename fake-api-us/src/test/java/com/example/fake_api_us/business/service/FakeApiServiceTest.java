package com.example.fake_api_us.business.service;

import com.example.fake_api_us.api.dto.ProductsDTO;
import com.example.fake_api_us.business.converter.ProdutoConverter;
import com.example.fake_api_us.infrastructure.client.FakeApiClient;
import com.example.fake_api_us.infrastructure.entities.ProdutoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.hamcrest.Matchers.is;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FakeApiServiceTest {

    @InjectMocks
    FakeApiService service;

    @Mock
    FakeApiClient client;

    @Mock
    ProdutoConverter converter;

    @Mock
    ProdutoService produtoService;

    @Test
    @DisplayName("Deve buscar produtos e gravar com sucesso")
    void deve_buscar_produtos_e_gravar_com_sucesso() {
        List<ProductsDTO> listaProdutosDTO = new ArrayList<>();
        ProductsDTO produtoDTO = ProductsDTO.builder().entityId("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
        listaProdutosDTO.add(produtoDTO);
        ProdutoEntity produtoEntity = ProdutoEntity.builder().id("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();

        when(client.buscaListaProdutos()).thenReturn(listaProdutosDTO);
        when(produtoService.existsPorNome(produtoDTO.getNome())).thenReturn(false);
        when(converter.toEntity(produtoDTO)).thenReturn(produtoEntity);
        when(produtoService.salvaProdutos(produtoEntity)).thenReturn(produtoEntity);
        when(produtoService.buscaTodosOsProdutos()).thenReturn(listaProdutosDTO);

        List<ProductsDTO> listaProdutosDTORetorno = service.buscaProdutos();

        assertEquals(listaProdutosDTO, listaProdutosDTORetorno);
        verify(client).buscaListaProdutos();
        verify(produtoService).existsPorNome(produtoDTO.getNome());
        verify(converter).toEntity(produtoDTO);
        verify(produtoService).salvaProdutos(produtoEntity);
        verify(produtoService).buscaTodosOsProdutos();
        verifyNoMoreInteractions(client, produtoService, converter);
    }

//    @Test
//    @DisplayName("Deve buscar produtos e não gravar caso retorno seja true")
//    void deve_buscar_produtos_e_nao_gravar_caso_retorno_seja_true() {
//        List<ProductsDTO> listaProdutosDTO = new ArrayList<>();
//        ProductsDTO produtoDTO = ProductsDTO.builder().entityId("1245").nome("Jaqueta Vermelha").categoria("Roupas").descricao("Jaqueta Vermelha com bolsos laterais e Listras").preco(new BigDecimal(250.00)).build();
//        listaProdutosDTO.add(produtoDTO);
//
//        when(client.buscaListaProdutos()).thenReturn(listaProdutosDTO);
//        when(produtoService.existsPorNome(produtoDTO.getNome())).thenReturn(true);
//
//        verify(client).buscaListaProdutos();
//        verify(produtoService).existsPorNome(produtoDTO.getNome());
//        verifyNoMoreInteractions(client, produtoService);
//        verifyNoInteractions(converter);
//    }

}
